package com.jookmax.v7.brain.backtest

import com.jookmax.v7.brain.backtest.analytics.BacktestAnalytics
import com.jookmax.v7.brain.backtest.model.BacktestTrade
import com.jookmax.v7.brain.backtest.model.OpenBacktestPosition
import com.jookmax.v7.brain.decision.DecisionAction
import com.jookmax.v7.brain.intelligence.BacktestIntelligenceAdapter
import com.jookmax.v7.brain.learning.BacktestLearningAdapter
import com.jookmax.v7.brain.pipeline.BrainPipeline
import com.jookmax.v7.core.model.MarketCandle

import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class BacktestRunner @Inject constructor(

    private val historicalDataLoader: HistoricalDataLoader,

    private val brainPipeline: BrainPipeline,

    private val tradeExecutor: BacktestTradeExecutor,

    private val backtestAnalytics: BacktestAnalytics,

    private val backtestLearningAdapter: BacktestLearningAdapter,

    private val backtestIntelligenceAdapter: BacktestIntelligenceAdapter

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


    private fun processCandle(

        candle: MarketCandle

    ) {

        tradeExecutor.evaluateCandle(candle)

        if (tradeExecutor.hasOpenPosition()) {

            return
        }

        val result =

            brainPipeline.execute(candle)

        val decision =

            result.validatedDecision

        if (

            !decision.approved ||

            decision.finalAction == DecisionAction.HOLD

        ) {

            return
        }

        val risk =

            result.context.riskDecision

        if (risk.positionSize <= 0.0) {

            return
        }

        tradeExecutor.openPosition(

            OpenBacktestPosition(

                action = decision.finalAction,

                entryPrice = candle.close,

                stopLoss = risk.stopLoss,

                takeProfit = risk.takeProfit,

                positionSize = risk.positionSize,

                openedAt = candle.timestamp

            )

        )
    }


    private fun createResult(

        candles: List<MarketCandle>

    ): BacktestResult {

        val trades: List<BacktestTrade> =

            tradeExecutor.getCompletedTrades()

        val winningTrades =

            trades.count {

                it.profitLoss > 0.0

            }

        val losingTrades =

            trades.count {

                it.profitLoss <= 0.0

            }

        val netProfit =

            trades.sumOf {

                it.profitLoss

            }

        val winRate =

            if (trades.isEmpty()) {

                0.0

            } else {

                winningTrades.toDouble() /

                        trades.size.toDouble()

            }

        val buySignals =

            trades.count {

                it.action == DecisionAction.BUY

            }

        val sellSignals =

            trades.count {

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

            metrics = metrics

        )
    }

}
