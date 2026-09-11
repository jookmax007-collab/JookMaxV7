package com.jookmax.v7.presentation.dashboard


data class DashboardState(


    val symbol: String = "XAU/USD",


    val price: Double = 0.0,


    val changePercent: Double = 0.0,



    // Brain Decision

    val brainDecision: String = "WAIT",


    val confidence: Int = 0,



    // Latest Intelligence Decision

    val latestAction: String = "WAIT",


    val validationStatus: String = "UNKNOWN",


    val validationScore: Double = 0.0,


    val riskAllowed: Boolean = false,


    val learningReward: Double = 0.0,



    // Market

    val marketTrend: String = "UNKNOWN",



    // Risk

    val riskStatus: String = "UNKNOWN",



    // Engine

    val engineStatus: String = "OFFLINE",



    // Monitoring metrics

    val totalDecisions: Long = 0,


    val buyDecisions: Long = 0,


    val sellDecisions: Long = 0,


    val holdDecisions: Long = 0,


    val averageDecisionConfidence: Double = 0.0


)