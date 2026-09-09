package com.jookmax.v7.brain.backtest

import com.jookmax.v7.brain.backtest.analytics.BacktestMetrics
import com.jookmax.v7.brain.backtest.model.BacktestTrade


data class BacktestResult(

    val totalCandles: Int,

    val totalTrades: Int,

    val winningTrades: Int,

    val losingTrades: Int,

    val netProfit: Double,

    val winRate: Double,

    val buySignals: Int,

    val sellSignals: Int,

    val holdSignals: Int,

    val startTime: Long,

    val endTime: Long,

    val trades: List<BacktestTrade>,

    val metrics: BacktestMetrics

)
