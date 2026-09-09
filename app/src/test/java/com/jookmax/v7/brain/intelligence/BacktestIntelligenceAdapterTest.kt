package com.jookmax.v7.brain.intelligence

import com.jookmax.v7.brain.backtest.BacktestResult
import com.jookmax.v7.brain.backtest.analytics.BacktestMetrics
import com.jookmax.v7.brain.backtest.model.BacktestTrade
import com.jookmax.v7.brain.decision.DecisionAction
import org.junit.Assert.assertEquals
import org.junit.Test


class BacktestIntelligenceAdapterTest {


    @Test
    fun `backtest result should create intelligence memories`() {


        val memory = DecisionMemory()

        val adapter = BacktestIntelligenceAdapter(
            memory
        )


        val trades = listOf(

            BacktestTrade(

                action = DecisionAction.BUY,

                entryPrice = 2500.0,

                exitPrice = 2510.0,

                stopLoss = 2490.0,

                takeProfit = 2520.0,

                positionSize = 1.0,

                profitLoss = 10.0,

                openedAt = 1L,

                closedAt = 2L,

                success = true

            ),

            BacktestTrade(

                action = DecisionAction.SELL,

                entryPrice = 2500.0,

                exitPrice = 2505.0,

                stopLoss = 2510.0,

                takeProfit = 2490.0,

                positionSize = 1.0,

                profitLoss = -5.0,

                openedAt = 3L,

                closedAt = 4L,

                success = false

            )

        )


        val result = BacktestResult(

            totalCandles = 100,

            totalTrades = 2,

            winningTrades = 1,

            losingTrades = 1,

            netProfit = 5.0,

            winRate = 0.5,

            buySignals = 1,

            sellSignals = 1,

            holdSignals = 0,

            startTime = 1L,

            endTime = 4L,

            trades = trades,

            metrics = BacktestMetrics(

                totalTrades = 2,

                winRate = 0.5,

                profitFactor = 2.0,

                averageWin = 10.0,

                averageLoss = 5.0,

                expectancy = 2.5,

                maxDrawdown = 5.0

            )

        )


        adapter.learnFromBacktest(result)


        assertEquals(

            2,

            memory.size()

        )

    }

}
