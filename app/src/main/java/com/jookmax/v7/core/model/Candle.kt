package com.jookmax.v7.core.model

/**
 * Represents OHLC market candle data.
 */
data class Candle(
    val symbol: Symbol,
    val timeFrame: TimeFrame,
    val timestamp: Long,

    val open: Double,
    val high: Double,
    val low: Double,
    val close: Double,

    val volume: Double = 0.0
)