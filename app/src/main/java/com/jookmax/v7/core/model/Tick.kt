package com.jookmax.v7.core.model


/**
 * Represents a single market tick update.
 *
 * Used for real-time market data streams.
 */
data class Tick(

    val symbol: Symbol,

    val price: Double,

    val timestamp: Long,

    val bid: Double? = null,

    val ask: Double? = null

)