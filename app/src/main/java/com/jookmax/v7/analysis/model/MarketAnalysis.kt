package com.jookmax.v7.analysis.model


import com.jookmax.v7.core.model.Symbol



data class MarketAnalysis(

    val symbol: Symbol,

    val trend: String,

    val rsi: Double,

    val movingAverage: Double,

    val volatility: Double,

    val timestamp: Long

)