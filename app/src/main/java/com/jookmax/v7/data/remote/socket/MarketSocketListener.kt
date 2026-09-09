package com.jookmax.v7.data.remote.socket

import com.jookmax.v7.core.model.MarketTick

/**
 * Receives lifecycle and market data events from the WebSocket client.
 */
interface MarketSocketListener {

    /**
     * Called when the WebSocket connection is established.
     */
    fun onConnected()

    /**
     * Called when a market tick is received.
     */
    fun onTick(tick: MarketTick)

    /**
     * Called when the WebSocket connection is closed.
     *
     * @param code WebSocket close code.
     * @param reason Optional close reason.
     */
    fun onDisconnected(
        code: Int,
        reason: String?
    )

    /**
     * Called when the WebSocket encounters an error.
     */
    fun onFailure(
        throwable: Throwable
    )
}
