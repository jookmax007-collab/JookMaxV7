package com.jookmax.v7.brain.trading.model

import com.jookmax.v7.brain.backtest.model.BacktestLearningContext
import com.jookmax.v7.brain.decision.DecisionAction


data class TradePosition(

    val positionId: String,

    val action: DecisionAction,

    val entryPrice: Double,

    val stopLoss: Double,

    val takeProfit: Double,

    val positionSize: Double,

    val openedAt: Long,

    /**
     * Snapshot of intelligence state at entry time.
     *
     * Used after trade closes
     * for reward based learning.
     */
    val learningContext: BacktestLearningContext? = null

)