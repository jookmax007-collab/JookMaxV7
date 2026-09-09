package com.jookmax.v7.brain.backtest.analytics


data class BacktestMetrics(

    val totalTrades: Int,

    val winRate: Double,

    val profitFactor: Double,

    val averageWin: Double,

    val averageLoss: Double,

    val expectancy: Double,

    val maxDrawdown: Double

)
