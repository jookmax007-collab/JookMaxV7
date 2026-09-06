package com.jookmax.v7.core.model

/**
 * Represents latest market price snapshot.
 */
data class MarketPrice(
    val symbol: Symbol,
    val price: Double,
    val timestamp: Long,

    val bid: Double? = null,
    val ask: Double? = null
)