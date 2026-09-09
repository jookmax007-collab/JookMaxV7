package com.jookmax.v7.brain.backtest.analytics


data class EquityPoint(

    val timestamp: Long,

    val balance: Double

)


data class EquityCurve(

    val points: List<EquityPoint>

)