package com.jookmax.v7.core.model


/**
 * Represents historical market data.
 */
data class MarketHistory(

    val symbol: Symbol,

    val candles: List<Candle>,

    val timestamp: Long

)