package com.jookmax.v7.brain.backtest


import com.jookmax.v7.brain.pipeline.BrainPipeline
import javax.inject.Inject
import javax.inject.Singleton



/**
 * Backtest Execution Engine
 *
 * Flow:
 *
 * HistoricalDataLoader
 *          |
 *          v
 * MarketCandle
 *          |
 *          v
 * BrainPipeline
 *          |
 *          v
 * BrainExecutionResult
 *          |
 *          v
 * BacktestResult
 *
 */
@Singleton
class BacktestRunner @Inject constructor(

    private val dataLoader: HistoricalDataLoader,

    private val brainPipeline: BrainPipeline

) {



    fun run(): BacktestResult {


        val candles =

            dataLoader.load()



        var totalDecisions = 0

        var buySignals = 0

        var sellSignals = 0

        var holdSignals = 0



        val startTime =

            System.currentTimeMillis()



        candles.forEach { candle ->



            val result =

                brainPipeline.execute(

                    candle

                )



            totalDecisions++



            when(
                result.validatedDecision.finalAction
            ) {


                com.jookmax.v7.brain.decision.DecisionAction.BUY ->

                    buySignals++



                com.jookmax.v7.brain.decision.DecisionAction.SELL ->

                    sellSignals++



                com.jookmax.v7.brain.decision.DecisionAction.HOLD ->

                    holdSignals++

            }


        }



        val endTime =

            System.currentTimeMillis()



        return BacktestResult(

            totalCandles = candles.size,

            totalDecisions = totalDecisions,

            buySignals = buySignals,

            sellSignals = sellSignals,

            holdSignals = holdSignals,

            startTime = startTime,

            endTime = endTime

        )

    }


}