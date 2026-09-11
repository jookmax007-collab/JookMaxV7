package com.jookmax.v7.liquidity.model


data class LiquidityAnalysisResult(


    val events: List<LiquidityEvent> = emptyList(),


    val score: Double = 0.0


)