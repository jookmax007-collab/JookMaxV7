package com.jookmax.v7.brain.backtest


import com.jookmax.v7.brain.backtest.model.BacktestTrade
import com.jookmax.v7.brain.backtest.model.OpenBacktestPosition
import com.jookmax.v7.brain.decision.DecisionAction
import com.jookmax.v7.core.model.MarketCandle

import javax.inject.Inject
import javax.inject.Singleton



/**
 * Executes simulated trades during backtesting.
 *
 * Flow:
 *
 * ValidatedDecision
 *        +
 * RiskDecision
 *        |
 *        v
 * Open Position
 *        |
 *        v
 * Candle Evaluation
 *        |
 *        v
 * Closed BacktestTrade
 *
 */
@Singleton
class BacktestTradeExecutor @Inject constructor() {



    private var openPosition: OpenBacktestPosition? = null



    private val completedTrades =
        mutableListOf<BacktestTrade>()





    fun hasOpenPosition(): Boolean {

        return openPosition != null

    }







    fun openPosition(

        position: OpenBacktestPosition

    ) {

        openPosition = position

    }








    fun evaluateCandle(

        candle: MarketCandle

    ): BacktestTrade? {


        val position =

            openPosition
                ?: return null





        val exitPrice = when(position.action) {


            DecisionAction.BUY -> {


                when {


                    candle.low <= position.stopLoss ->

                        position.stopLoss



                    candle.high >= position.takeProfit ->

                        position.takeProfit



                    else -> null

                }

            }




            DecisionAction.SELL -> {


                when {


                    candle.high >= position.stopLoss ->

                        position.stopLoss



                    candle.low <= position.takeProfit ->

                        position.takeProfit



                    else -> null

                }

            }




            DecisionAction.HOLD -> null

        }





        if(exitPrice == null) {

            return null

        }





        return closePosition(

            exitPrice,

            candle.timestamp

        )

    }








    private fun closePosition(

        exitPrice: Double,

        timestamp: Long

    ): BacktestTrade {


        val position =

            openPosition!!





        val profitLoss = calculateProfitLoss(

            position,

            exitPrice

        )





        val trade = BacktestTrade(


            action = position.action,


            entryPrice = position.entryPrice,


            exitPrice = exitPrice,


            stopLoss = position.stopLoss,


            takeProfit = position.takeProfit,


            positionSize = position.positionSize,


            profitLoss = profitLoss,


            openedAt = position.openedAt,


            closedAt = timestamp,


            success = profitLoss > 0


        )




        completedTrades.add(trade)


        openPosition = null



        return trade

    }








    private fun calculateProfitLoss(

        position: OpenBacktestPosition,

        exitPrice: Double

    ): Double {


        val difference = when(position.action) {


            DecisionAction.BUY ->

                exitPrice - position.entryPrice



            DecisionAction.SELL ->

                position.entryPrice - exitPrice



            DecisionAction.HOLD ->

                0.0

        }



        return difference * position.positionSize

    }







    fun getCompletedTrades(): List<BacktestTrade> {

        return completedTrades.toList()

    }





    fun reset() {

        openPosition = null

        completedTrades.clear()

    }


}