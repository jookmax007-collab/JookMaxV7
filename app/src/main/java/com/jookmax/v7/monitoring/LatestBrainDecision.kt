package com.jookmax.v7.monitoring


data class LatestBrainDecision(

    val symbol: String,

    val action: String,

    val confidence: Double,

    val approved: Boolean,

    val validationScore: Double,

    val marketScore: Double,

    val riskAllowed: Boolean,

    val learningReward: Double,

    val timestamp: Long = System.currentTimeMillis()

)