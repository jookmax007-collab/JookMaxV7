package com.jookmax.v7.brain.intelligence.memory


data class CurrentMarketPattern(

    val symbol: String,

    val trend: String,

    val rsi: Double,

    val volatility: Double,

    val marketRegime: String,

    val session: String

)