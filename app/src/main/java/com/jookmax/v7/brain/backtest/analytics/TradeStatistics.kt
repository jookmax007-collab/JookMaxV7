package com.jookmax.v7.brain.backtest.analytics


data class TradeStatistics(

    val totalTrades: Int,

    val winningTrades: Int,

    val losingTrades: Int,

    val winRate: Double,

    val averageTrade: Double,

    val largestWin: Double,

    val largestLoss: Double,

    val consecutiveWins: Int,

    val consecutiveLosses: Int

)