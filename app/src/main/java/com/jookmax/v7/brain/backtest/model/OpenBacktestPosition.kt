package com.jookmax.v7.brain.backtest.model

import com.jookmax.v7.brain.decision.DecisionAction


/**
 * Represents currently opened simulated position.
 *
 * Created from:
 *
 * ValidatedDecision
 * +
 * RiskDecision
 *
 * +
 * Learning Context
 *
 */
data class OpenBacktestPosition(


    val action: DecisionAction,


    val entryPrice: Double,


    val stopLoss: Double,


    val takeProfit: Double,


    val positionSize: Double,


    val openedAt: Long,


    /**
     * Intelligence snapshot captured at entry time.
     *
     * Used later when trade closes:
     *
     * Trade Outcome
     *
     * +
     *
     * Reward
     *
     * +
     *
     * Learning Context
     *
     * =
     *
     * Learning Experience
     */
    val learningContext: BacktestLearningContext

)