package com.jookmax.v7.brain.backtest

import com.jookmax.v7.brain.backtest.model.BacktestTrade


/**
 * Result of one complete historical backtest.
 *
 * Contains:
 *
 * - Market sample size
 * - Executed trades
 * - Win/Loss statistics
 * - Profitability metrics
 * - Backtest period
 * - Trade history
 *
 */
data class BacktestResult(

    /**
     * Number of historical candles processed.
     */
    val totalCandles: Int,


    /**
     * Number of completed simulated trades.
     */
    val totalTrades: Int,


    /**
     * Number of profitable trades.
     */
    val winningTrades: Int,


    /**
     * Number of losing trades.
     */
    val losingTrades: Int,


    /**
     * Net profit/loss from all trades.
     */
    val netProfit: Double,


    /**
     * Winning trades ratio.
     *
     * Range:
     * 0.0 - 1.0
     */
    val winRate: Double,


    /**
     * Number of BUY executions.
     */
    val buySignals: Int,


    /**
     * Number of SELL executions.
     */
    val sellSignals: Int,


    /**
     * Number of ignored HOLD decisions.
     */
    val holdSignals: Int,


    /**
     * First candle timestamp.
     */
    val startTime: Long,


    /**
     * Last candle timestamp.
     */
    val endTime: Long,


    /**
     * Completed simulated trades.
     *
     * Used by analytics,
     * reward engine and learning layer.
     */
    val trades: List<BacktestTrade>

)