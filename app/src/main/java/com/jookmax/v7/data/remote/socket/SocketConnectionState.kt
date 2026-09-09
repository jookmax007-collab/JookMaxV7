package com.jookmax.v7.data.remote.socket

/**
 * Represents the current state of the market WebSocket connection.
 */
sealed interface SocketConnectionState {

    data object Disconnected : SocketConnectionState

    data object Connecting : SocketConnectionState

    data object Connected : SocketConnectionState

    data class Reconnecting(
        val attempt: Int,
        val delayMillis: Long
    ) : SocketConnectionState

    data class Failed(
        val message: String,
        val cause: Throwable? = null
    ) : SocketConnectionState
}
