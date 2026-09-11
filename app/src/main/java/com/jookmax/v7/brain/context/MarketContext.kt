package com.jookmax.v7.brain.context


data class MarketContext(

    val symbol: String,

    val price: Double,

    val trend: String,

    val rsi: Double,

    val volatility: Double,


    // Market Regime

    val marketRegime: String,


    // Trading Session

    val session: String,


    // External Market Factors

    val dxy: Double = 0.0,

    val yield: Double = 0.0,

    val newsRisk: Double = 0.0,


    // Market Structure Intelligence

    val structureDirection: String = "UNKNOWN",

    val bosDetected: Boolean = false,

    val chochDetected: Boolean = false,


    val lastSwingHigh: Double? = null,

    val lastSwingLow: Double? = null,


    val timestamp: Long = System.currentTimeMillis()

)