package com.jookmax.v7.core.model


/**
 * Represents OHLC market candle data.
 *
 * Example:
 * XAUUSD 1H Market Candle
 */
data class MarketCandle(

    val symbol: Symbol,

    val timeFrame: TimeFrame,

    val timestamp: Long,


    val open: Double,

    val high: Double,

    val low: Double,

    val close: Double,


    val volume: Double = 0.0

)
