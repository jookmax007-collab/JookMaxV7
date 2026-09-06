package com.jookmax.v7.core.model


/**
 * Represents a single real-time market tick.
 *
 * Used for live market streams,
 * price updates and candle generation.
 */
data class MarketTick(

    /**
     * Trading symbol.
     * Example: XAUUSD
     */
    val symbol: Symbol,


    /**
     * Current market price.
     */
    val price: Double,


    /**
     * Tick timestamp.
     */
    val timestamp: Long,


    /**
     * Bid price.
     */
    val bid: Double? = null,


    /**
     * Ask price.
     */
    val ask: Double? = null,


    /**
     * Trading volume if available.
     */
    val volume: Double? = null

)