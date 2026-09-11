package com.jookmax.v7.brain.backtest


import com.jookmax.v7.brain.backtest.analytics.BacktestAnalytics
import com.jookmax.v7.brain.backtest.model.BacktestLearningContext
import com.jookmax.v7.brain.backtest.model.BacktestTrade
import com.jookmax.v7.brain.backtest.model.OpenBacktestPosition
import com.jookmax.v7.brain.decision.DecisionAction
import com.jookmax.v7.brain.intelligence.BacktestIntelligenceAdapter
import com.jookmax.v7.brain.learning.BacktestLearningAdapter
import com.jookmax.v7.brain.pipeline.BrainExecutor
import com.jookmax.v7.brain.reward.RewardEngine
import com.jookmax.v7.brain.reward.analytics.RewardAnalytics
import com.jookmax.v7.domain.repository.RewardExperienceRepository
import com.jookmax.v7.core.model.MarketCandle

import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class BacktestRunner @Inject constructor(


    private val historicalDataLoader: HistoricalDataLoader,


    private val brainExecutor: BrainExecutor,


    private val tradeExecutor: BacktestTradeExecutor,


    private val backtestAnalytics: BacktestAnalytics,


    private val backtestLearningAdapter: BacktestLearningAdapter,


    private val backtestIntelligenceAdapter: BacktestIntelligenceAdapter,


    private val rewardEngine: RewardEngine,


    private val rewardExperienceRepository: RewardExperienceRepository,


    private val rewardAnalytics: RewardAnalytics


) {



    suspend fun run(): BacktestResult {


        tradeExecutor.reset()


        val candles = historicalDataLoader.load()



        candles.forEach { candle ->

            processCandle(candle)

        }



        val result = createResult(candles)



        backtestLearningAdapter.learnFromBacktest(result)


        backtestIntelligenceAdapter.learnFromBacktest(result)



        return result

    }






    private suspend fun processCandle(

        candle: MarketCandle

    ) {


        val outcome = tradeExecutor.evaluateOutcome(candle)



        if (outcome != null) {


            rewardEngine.evaluate(outcome)

        }





        if (tradeExecutor.hasOpenPosition()) {


            return

        }





        val result = brainExecutor.execute(candle)



        val decision = result.validatedDecision



        if (

            !decision.approved ||

            decision.finalAction == DecisionAction.HOLD

        ) {


            return

        }





        val risk = result.context.riskDecision



        if (risk.positionSize <= 0.0) {


            return

        }





        val marketAnalysis = result.context.marketAnalysis





        tradeExecutor.openPosition(



            OpenBacktestPosition(


                action = decision.finalAction,


                entryPrice = candle.close,


                stopLoss = risk.stopLoss,


                takeProfit = risk.takeProfit,


                positionSize = risk.positionSize,


                openedAt = candle.timestamp,



                learningContext = BacktestLearningContext(


                    action = decision.finalAction,


                    confidence = result.decision.confidence,


                    positionSize = risk.positionSize,


                    stopLoss = risk.stopLoss,


                    takeProfit = risk.takeProfit,


                    symbol = marketAnalysis.symbol.code,


                    trend = marketAnalysis.trend,


                    rsi = marketAnalysis.rsi,


                    movingAverage = marketAnalysis.movingAverage,


                    volatility = marketAnalysis.volatility,


                    openedAt = candle.timestamp

                )

            )

        )

    }








    private suspend fun createResult(

        candles: List<MarketCandle>

    ): BacktestResult {



        val trades: List<BacktestTrade> =

            tradeExecutor.getCompletedTrades()






        val experiences =

            rewardExperienceRepository.getExperiences()





        val rewardReport =

            rewardAnalytics.analyze(

                experiences

            )







        val winningTrades = trades.count {


            it.profitLoss > 0.0

        }





        val losingTrades = trades.count {


            it.profitLoss <= 0.0

        }





        val netProfit = trades.sumOf {


            it.profitLoss

        }






        val winRate =

            if (trades.isEmpty()) {


                0.0


            } else {


                winningTrades.toDouble() /

                        trades.size.toDouble()

            }






        val buySignals = trades.count {


            it.action == DecisionAction.BUY

        }





        val sellSignals = trades.count {


            it.action == DecisionAction.SELL

        }






        val metrics =

            backtestAnalytics.analyze(trades)







        return BacktestResult(



            totalCandles = candles.size,


            totalTrades = trades.size,


            winningTrades = winningTrades,


            losingTrades = losingTrades,


            netProfit = netProfit,


            winRate = winRate,


            buySignals = buySignals,


            sellSignals = sellSignals,


            holdSignals = 0,


            startTime = candles.firstOrNull()?.timestamp ?: 0L,


            endTime = candles.lastOrNull()?.timestamp ?: 0L,


            trades = trades,


            metrics = metrics,


            rewardAnalytics = rewardReport

        )

    }


}