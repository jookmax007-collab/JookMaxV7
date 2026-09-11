package com.jookmax.v7.brain.backtest.model


import com.jookmax.v7.brain.decision.DecisionAction


/**
 * Learning context captured when a backtest position is opened.
 *
 * Keeps intelligence information alive until trade completion.
 *
 * Flow:
 *
 * Brain Decision
 * +
 * Risk Decision
 * +
 * Market Analysis
 *
 *        ↓
 *
 * BacktestLearningContext
 *
 *        ↓
 *
 * Reward
 *
 *        ↓
 *
 * Learning Experience
 */
data class BacktestLearningContext(


    val action: DecisionAction,


    val confidence: Double,


    val positionSize: Double,


    val stopLoss: Double,


    val takeProfit: Double,


    val symbol: String,


    val trend: String,


    val rsi: Double,


    val movingAverage: Double,


    val volatility: Double,


    val openedAt: Long

)