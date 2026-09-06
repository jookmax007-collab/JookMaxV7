package com.jookmax.v7.core.model

import com.jookmax.v7.core.model.Symbol
import com.jookmax.v7.core.model.TimeFrame


/**
 * Represents OHLC market candle data.
 *
 * Example:
 * XAUUSD 1H Candle
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