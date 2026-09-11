package com.jookmax.v7.brain.trading


import com.jookmax.v7.brain.decision.DecisionAction
import com.jookmax.v7.brain.trading.model.TradeExitReason
import com.jookmax.v7.brain.trading.model.TradeOutcome
import com.jookmax.v7.brain.trading.model.TradePosition
import com.jookmax.v7.core.model.MarketCandle

import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class PositionTracker @Inject constructor() {



    private var currentPosition: TradePosition? = null




    fun hasPosition(): Boolean {

        return currentPosition != null

    }




    fun open(

        position: TradePosition

    ) {

        currentPosition = position

    }






    fun evaluate(

        candle: MarketCandle

    ): TradeOutcome? {


        val position =

            currentPosition
                ?: return null





        val exit = when(position.action) {



            DecisionAction.BUY -> {


                when {


                    candle.low <= position.stopLoss ->

                        Pair(
                            position.stopLoss,
                            TradeExitReason.STOP_LOSS
                        )



                    candle.high >= position.takeProfit ->

                        Pair(
                            position.takeProfit,
                            TradeExitReason.TAKE_PROFIT
                        )


                    else -> null

                }

            }





            DecisionAction.SELL -> {


                when {


                    candle.high >= position.stopLoss ->

                        Pair(
                            position.stopLoss,
                            TradeExitReason.STOP_LOSS
                        )



                    candle.low <= position.takeProfit ->

                        Pair(
                            position.takeProfit,
                            TradeExitReason.TAKE_PROFIT
                        )


                    else -> null

                }

            }





            DecisionAction.HOLD -> null

        }





        exit ?: return null





        val exitPrice = exit.first

        val reason = exit.second





        val profitLoss = calculateProfitLoss(

            position,

            exitPrice

        )







        val outcome = TradeOutcome(


            positionId = position.positionId,


            action = position.action,


            entryPrice = position.entryPrice,


            exitPrice = exitPrice,


            stopLoss = position.stopLoss,


            takeProfit = position.takeProfit,


            positionSize = position.positionSize,


            openedAt = position.openedAt,


            closedAt = candle.timestamp,


            exitReason = reason,


            profitLoss = profitLoss,


            success = profitLoss > 0,



            learningContext = position.learningContext

        )





        currentPosition = null





        return outcome

    }








    private fun calculateProfitLoss(

        position: TradePosition,

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








    fun reset() {

        currentPosition = null

    }


}