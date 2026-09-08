package com.jookmax.v7.brain.decision


import com.jookmax.v7.brain.risk.RiskDecision



data class DecisionContext(

    val marketScore: Double,

    val riskDecision: RiskDecision,

    val learningReward: Double

)