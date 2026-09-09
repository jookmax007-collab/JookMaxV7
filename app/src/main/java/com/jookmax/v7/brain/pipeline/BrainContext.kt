package com.jookmax.v7.brain.pipeline


import com.jookmax.v7.analysis.model.MarketAnalysis
import com.jookmax.v7.brain.decision.DecisionResult
import com.jookmax.v7.brain.risk.RiskDecision



data class BrainContext(

    val marketAnalysis: MarketAnalysis,

    val riskDecision: RiskDecision,

    val learningReward: Double,

    val decisionResult: DecisionResult? = null

)
