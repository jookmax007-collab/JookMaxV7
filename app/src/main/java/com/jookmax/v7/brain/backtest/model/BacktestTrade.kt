package com.jookmax.v7.brain.backtest.model


import com.jookmax.v7.brain.decision.DecisionAction



data class BacktestTrade(


    val action: DecisionAction,


    val entryPrice: Double,


    val exitPrice: Double?,


    val stopLoss: Double,


    val takeProfit: Double,


    val positionSize: Double,


    val profitLoss: Double,


    val openedAt: Long,


    val closedAt: Long?,


    val success: Boolean,



    /**
     * Intelligence snapshot
     *
     * Captured when trade was opened.
     */
    val learningContext: BacktestLearningContext? = null


)