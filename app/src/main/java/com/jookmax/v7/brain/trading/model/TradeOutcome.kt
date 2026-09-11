package com.jookmax.v7.brain.trading.model


import com.jookmax.v7.brain.backtest.model.BacktestLearningContext
import com.jookmax.v7.brain.decision.DecisionAction



data class TradeOutcome(


    val positionId: String,


    val action: DecisionAction,


    val entryPrice: Double,


    val exitPrice: Double,


    val stopLoss: Double,


    val takeProfit: Double,


    val positionSize: Double,


    val openedAt: Long,


    val closedAt: Long,


    val exitReason: TradeExitReason,


    val profitLoss: Double,


    val success: Boolean,


    /**
     * Intelligence snapshot captured at entry.
     *
     * Used by reward system
     * to create learning experience.
     */
    val learningContext: BacktestLearningContext? = null

)