package com.jookmax.v7.core.model


/**
 * Represents a single market candle.
 *
 * Used for historical market analysis,
 * charting, indicators and AI decision systems.
 */
data class MarketCandle(

    /**
     * Trading symbol
     * Example: XAUUSD
     */
    val symbol: Symbol,


    /**
     * Candle timeframe in minutes.
     *
     * Examples:
     * 1  = 1 minute
     * 5  = 5 minutes
     * 15 = 15 minutes
     * 60 = 1 hour
     */
    val timeframe: Int,


    /**
     * Candle opening timestamp.
     */
    val timestamp: Long,


    /**
     * Opening price.
     */
    val open: Double,


    /**
     * Highest price during candle period.
     */
    val high: Double,


    /**
     * Lowest price during candle period.
     */
    val low: Double,


    /**
     * Closing price.
     */
    val close: Double,


    /**
     * Trading volume.
     */
    val volume: Double? = null

)