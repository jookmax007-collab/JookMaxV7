package com.jookmax.v7.brain.pipeline


import com.jookmax.v7.analysis.model.MarketAnalysis
import com.jookmax.v7.brain.context.MarketContext
import com.jookmax.v7.brain.decision.DecisionResult
import com.jookmax.v7.brain.risk.RiskDecision
import com.jookmax.v7.liquidity.model.LiquidityContext
import com.jookmax.v7.structure.model.MarketStructure



data class BrainContext(


    val marketAnalysis: MarketAnalysis,


    val marketStructure: MarketStructure,


    val marketContext: MarketContext,


    val liquidityContext: LiquidityContext,


    val riskDecision: RiskDecision,


    val learningReward: Double,


    val decisionResult: DecisionResult? = null


)