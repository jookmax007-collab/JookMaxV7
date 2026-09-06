package com.jookmax.v7.core.model


/**
 * Represents historical market data.
 *
 * Contains a collection of market candles
 * used for analysis, charts and AI processing.
 */
data class MarketHistory(

    /**
     * Trading symbol.
     */
    val symbol: Symbol,


    /**
     * Candle timeframe.
     *
     * Example:
     * 1  = 1 minute
     * 5  = 5 minutes
     * 60 = 1 hour
     */
    val timeframe: Int,


    /**
     * Historical candles ordered by timestamp.
     */
    val candles: List<MarketCandle>,


    /**
     * Last update timestamp.
     */
    val updatedAt: Long

)