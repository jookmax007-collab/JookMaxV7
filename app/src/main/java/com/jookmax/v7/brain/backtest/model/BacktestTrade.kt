package com.jookmax.v7.brain.backtest.model

import com.jookmax.v7.brain.decision.DecisionAction

/**
 * Represents one simulated backtest trade.
 *
 * A trade is created from the validated brain decision
 * and the risk parameters calculated by the Risk Engine.
 */
data class BacktestTrade(

    /**
     * Final validated trading action.
     */
    val action: DecisionAction,

    /**
     * Price at which the simulated position is opened.
     */
    val entryPrice: Double,

    /**
     * Price at which the simulated position is closed.
     *
     * Null while the trade is still open.
     */
    val exitPrice: Double?,

    /**
     * Stop-loss price calculated by the Risk Engine.
     */
    val stopLoss: Double,

    /**
     * Take-profit price calculated by the Risk Engine.
     */
    val takeProfit: Double,

    /**
     * Simulated position size.
     */
    val positionSize: Double,

    /**
     * Realized profit or loss.
     */
    val profitLoss: Double,

    /**
     * Timestamp when the trade was opened.
     */
    val openedAt: Long,

    /**
     * Timestamp when the trade was closed.
     *
     * Null while the trade is still open.
     */
    val closedAt: Long?,

    /**
     * Whether the completed trade was profitable.
     */
    val success: Boolean

)
