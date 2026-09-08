package com.jookmax.v7.brain.pipeline


import com.jookmax.v7.brain.decision.DecisionResult
import com.jookmax.v7.brain.market.MarketAnalysis
import com.jookmax.v7.brain.risk.RiskResult



/**
 * Represents complete brain state during execution.
 *
 * Contains outputs from:
 * - MarketBrain
 * - RiskBrain
 * - LearningBrain
 *
 * Decision result is attached after DecisionEngine execution.
 */
data class BrainContext(


    val marketAnalysis: MarketAnalysis,


    val riskResult: RiskResult,


    val learningReward: Double,


    val decisionResult: DecisionResult? = null


)