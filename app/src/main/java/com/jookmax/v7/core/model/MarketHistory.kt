package com.jookmax.v7.core.model


/**
 * Represents historical market data.
 *
 * Contains a collection of market candles
 * used for analysis, indicators and AI systems.
 */
data class MarketHistory(


    /**
     * Trading symbol.
     *
     * Example:
     * XAUUSD
     */
    val symbol: Symbol,


    /**
     * Historical candle list.
     */
    val candles: List<MarketCandle>,


    /**
     * History creation/update timestamp.
     */
    val timestamp: Long

)
