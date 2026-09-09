package com.jookmax.v7.brain.backtest.analytics


data class PerformanceReport(

    val equityCurve: EquityCurve,

    val tradeStatistics: TradeStatistics,

    val riskMetrics: RiskMetrics,

    val generatedAt: Long = System.currentTimeMillis()

)
