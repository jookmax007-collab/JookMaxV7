package com.jookmax.v7.presentation.dashboard


data class DashboardState(

    val symbol: String = "XAU/USD",

    val price: Double = 0.0,

    val changePercent: Double = 0.0,


    val brainDecision: String = "WAIT",

    val confidence: Int = 0,


    val marketTrend: String = "UNKNOWN",

    val riskStatus: String = "UNKNOWN",

    val engineStatus: String = "OFFLINE",


    // Monitoring metrics

    val totalDecisions: Long = 0,

    val buyDecisions: Long = 0,

    val sellDecisions: Long = 0,

    val holdDecisions: Long = 0,

    val averageDecisionConfidence: Double = 0.0

)
