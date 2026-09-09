package com.jookmax.v7.brain.backtest.analytics


data class RiskMetrics(

    val maxDrawdown: Double,

    val sharpeRatio: Double,

    val recoveryFactor: Double,

    val riskRewardRatio: Double

)
