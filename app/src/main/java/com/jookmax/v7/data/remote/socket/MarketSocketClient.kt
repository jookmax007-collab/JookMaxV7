package com.jookmax.v7.data.remote.socket

import com.jookmax.v7.core.model.MarketTick
import com.jookmax.v7.core.model.Symbol
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import org.json.JSONObject
import java.time.Instant

/**
 * WebSocket client for the goldprice.dev real-time market feed.
 *
 * Protocol:
 * 1. Connect
 * 2. Authenticate
 * 3. Subscribe to XAU-USD-SPOT
 * 4. Receive tick frames
 */
class MarketSocketClient(
    private val apiKey: String,
    private val okHttpClient: OkHttpClient = OkHttpClient()
) {

    companion object {
        private const val STREAM_URL = "wss://api.goldprice.dev/v1/stream"
        private const val XAU_SYMBOL = "XAU-USD-SPOT"
    }

    private val _connectionState =
        MutableStateFlow<SocketConnectionState>(
            SocketConnectionState.Disconnected
        )

    val connectionState: StateFlow<SocketConnectionState> =
        _connectionState.asStateFlow()

    private var webSocket: WebSocket? = null

    private var listener: MarketSocketListener? = null

    fun setListener(listener: MarketSocketListener?) {
        this.listener = listener
    }

    fun connect() {
        if (apiKey.isBlank()) {
            val exception = IllegalArgumentException(
                "goldprice.dev API key must not be blank"
            )

            _connectionState.value =
                SocketConnectionState.Failed(
                    message = exception.message ?: "API key is missing",
                    cause = exception
                )

            listener?.onFailure(exception)
            return
        }

        if (_connectionState.value is SocketConnectionState.Connecting ||
            _connectionState.value is SocketConnectionState.Connected
        ) {
            return
        }

        _connectionState.value = SocketConnectionState.Connecting

        val request = Request.Builder()
            .url(STREAM_URL)
            .build()

        webSocket = okHttpClient.newWebSocket(
            request,
            createWebSocketListener()
        )
    }

    fun subscribe(symbols: List<String> = listOf(XAU_SYMBOL)) {
        if (symbols.isEmpty()) {
            return
        }

        val payload = JSONObject()
            .put("action", "subscribe")
            .put("symbols", symbols)

        webSocket?.send(payload.toString())
    }

    fun ping() {
        val payload = JSONObject()
            .put("action", "ping")

        webSocket?.send(payload.toString())
    }

    fun disconnect() {
        webSocket?.close(
            1000,
            "Client disconnect"
        )

        webSocket = null

        _connectionState.value =
            SocketConnectionState.Disconnected
    }

    private fun createWebSocketListener(): WebSocketListener =
        object : WebSocketListener() {

            override fun onOpen(
                webSocket: WebSocket,
                response: Response
            ) {
                _connectionState.value =
                    SocketConnectionState.Connected

                val authPayload = JSONObject()
                    .put("action", "auth")
                    .put("api_key", apiKey)

                webSocket.send(authPayload.toString())

                listener?.onConnected()
            }

            override fun onMessage(
                webSocket: WebSocket,
                text: String
            ) {
                handleMessage(webSocket, text)
            }

            override fun onClosing(
                webSocket: WebSocket,
                code: Int,
                reason: String
            ) {
                webSocket.close(code, reason)

                _connectionState.value =
                    SocketConnectionState.Disconnected
            }

            override fun onClosed(
                webSocket: WebSocket,
                code: Int,
                reason: String
            ) {
                this@MarketSocketClient.webSocket = null

                _connectionState.value =
                    SocketConnectionState.Disconnected

                listener?.onDisconnected(
                    code = code,
                    reason = reason
                )
            }

            override fun onFailure(
                webSocket: WebSocket,
                t: Throwable,
                response: Response?
            ) {
                this@MarketSocketClient.webSocket = null

                _connectionState.value =
                    SocketConnectionState.Failed(
                        message = t.message ?: "WebSocket failure",
                        cause = t
                    )

                listener?.onFailure(t)
            }
        }

    private fun handleMessage(
        webSocket: WebSocket,
        text: String
    ) {
        try {
            val json = JSONObject(text)
            val type = json.optString("type")

            when (type) {

                "welcome" -> {
                    subscribe()
                }

                "subscribed" -> {
                    // Subscription confirmed by the provider.
                }

                "tick" -> {
                    val tick = parseTick(json)

                    if (tick != null) {
                        listener?.onTick(tick)
                    }
                }

                "heartbeat" -> {
                    // Provider heartbeat requires no response.
                }

                "pong" -> {
                    // Ping response received.
                }

                "error" -> {
                    val code = json.optString(
                        "code",
                        "unknown_error"
                    )

                    val message = json.optString(
                        "message",
                        "Unknown WebSocket error"
                    )

                    val exception = IllegalStateException(
                        "$code: $message"
                    )

                    _connectionState.value =
                        SocketConnectionState.Failed(
                            message = exception.message
                                ?: "Provider error",
                            cause = exception
                        )

                    listener?.onFailure(exception)
                }
            }

        } catch (exception: Exception) {
            _connectionState.value =
                SocketConnectionState.Failed(
                    message = exception.message
                        ?: "Failed to parse WebSocket message",
                    cause = exception
                )

            listener?.onFailure(exception)
        }
    }

    private fun parseTick(
        json: JSONObject
    ): MarketTick? {

        val symbolCode = json
            .optString("symbol")
            .takeIf { it.isNotBlank() }
            ?: return null

        val price = json
            .optString("price")
            .toDoubleOrNull()
            ?: return null

        val computedAt = json
            .optString("computed_at")
            .takeIf { it.isNotBlank() }
            ?: return null

        val timestamp = try {
            Instant.parse(computedAt)
                .toEpochMilli()
        } catch (_: Exception) {
            return null
        }

        return MarketTick(
            symbol = Symbol(
                code = symbolCode
            ),
            price = price,
            timestamp = timestamp
        )
    }
}
