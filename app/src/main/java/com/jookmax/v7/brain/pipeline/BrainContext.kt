package com.jookmax.v7.brain.pipeline


import com.jookmax.v7.brain.market.MarketAnalysis
import com.jookmax.v7.brain.risk.RiskResult


data class BrainContext(

    val marketAnalysis: MarketAnalysis,

    val riskResult: RiskResult,

    val learningReward: Double

)