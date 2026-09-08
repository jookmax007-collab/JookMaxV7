package com.jookmax.v7.brain.backtest


data class BacktestResult(

    val totalCandles: Int,

    val totalDecisions: Int,

    val buySignals: Int,

    val sellSignals: Int,

    val holdSignals: Int,

    val startTime: Long,

    val endTime: Long

)