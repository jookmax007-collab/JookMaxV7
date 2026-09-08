package com.jookmax.v7.brain.decision


import com.jookmax.v7.analysis.model.MarketAnalysis
import com.jookmax.v7.brain.risk.RiskDecision



data class DecisionContext(

    val marketAnalysis: MarketAnalysis,

    val riskDecision: RiskDecision,

    val learningReward: Double,

    val marketScore: Double

)