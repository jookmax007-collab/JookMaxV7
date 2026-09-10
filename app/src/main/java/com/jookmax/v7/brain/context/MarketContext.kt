package com.jookmax.v7.brain.context


data class MarketContext(

    val symbol: String,

    val price: Double,

    val trend: String,

    val rsi: Double,

    val volatility: Double,

    val marketRegime: String,

    val session: String,

    val dxy: Double = 0.0,

    val yield: Double = 0.0,

    val newsRisk: Double = 0.0,

    val timestamp: Long = System.currentTimeMillis()

)