package com.jookmax.v7.brain.intelligence


import com.jookmax.v7.brain.backtest.BacktestResult
import com.jookmax.v7.brain.backtest.analytics.BacktestMetrics
import com.jookmax.v7.brain.backtest.model.BacktestLearningContext
import com.jookmax.v7.brain.backtest.model.BacktestTrade
import com.jookmax.v7.brain.decision.DecisionAction
import com.jookmax.v7.brain.intelligence.memory.DecisionPattern
import com.jookmax.v7.domain.repository.PersistentDecisionMemoryRepository

import kotlinx.coroutines.runBlocking

import org.junit.Assert.assertEquals
import org.junit.Test



class BacktestIntelligenceAdapterTest {


    @Test
    fun `backtest result should create intelligence memories`() = runBlocking {


        val memory =
            FakePersistentDecisionMemoryRepository()


        val adapter =
            BacktestIntelligenceAdapter(memory)



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

                success = true,


                learningContext = BacktestLearningContext(

                    action = DecisionAction.BUY,

                    confidence = 0.90,

                    positionSize = 1.0,

                    stopLoss = 2490.0,

                    takeProfit = 2520.0,

                    symbol = "XAUUSD",

                    trend = "BULLISH",

                    rsi = 55.0,

                    movingAverage = 2505.0,

                    volatility = 0.20,

                    openedAt = 1L

                )

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

                success = false,


                learningContext = BacktestLearningContext(

                    action = DecisionAction.SELL,

                    confidence = 0.70,

                    positionSize = 1.0,

                    stopLoss = 2510.0,

                    takeProfit = 2490.0,

                    symbol = "XAUUSD",

                    trend = "BEARISH",

                    rsi = 45.0,

                    movingAverage = 2502.0,

                    volatility = 0.15,

                    openedAt = 3L

                )

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

            ),


            rewardAnalytics =
                com.jookmax.v7.brain.reward.analytics.RewardAnalyticsResult(

                    totalExperiences = 2,

                    successfulTrades = 1,

                    failedTrades = 1,

                    averageReward = 2.5,

                    totalReward = 5.0,

                    winRate = 0.5,

                    bestAction = DecisionAction.BUY,

                    worstAction = DecisionAction.SELL

                )

        )



        adapter.learnFromBacktest(result)



        assertEquals(

            2,

            memory.count()

        )

    }

}





class FakePersistentDecisionMemoryRepository :

    PersistentDecisionMemoryRepository {



    private val items =
        mutableListOf<DecisionPattern>()



    override suspend fun save(
        pattern: DecisionPattern
    ) {

        items.add(pattern)

    }



    override suspend fun saveAll(
        patterns: List<DecisionPattern>
    ) {

        items.addAll(patterns)

    }



    override suspend fun getAll():
            List<DecisionPattern> {

        return items

    }



    override suspend fun getLatest():
            DecisionPattern? {

        return items.lastOrNull()

    }



    override suspend fun count():
            Int {

        return items.size

    }



    override suspend fun clear() {

        items.clear()

    }

}