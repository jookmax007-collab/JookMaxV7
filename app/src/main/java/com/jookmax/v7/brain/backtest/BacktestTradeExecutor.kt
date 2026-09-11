package com.jookmax.v7.brain.backtest


import com.jookmax.v7.brain.backtest.model.BacktestTrade
import com.jookmax.v7.brain.backtest.model.OpenBacktestPosition
import com.jookmax.v7.brain.trading.PositionTracker
import com.jookmax.v7.brain.trading.model.TradeOutcome
import com.jookmax.v7.brain.trading.model.TradePosition
import com.jookmax.v7.core.model.MarketCandle

import java.util.UUID

import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class BacktestTradeExecutor @Inject constructor(


    private val positionTracker: PositionTracker


) {



    private val completedTrades =

        mutableListOf<BacktestTrade>()





    private var lastOutcome: TradeOutcome? = null







    fun hasOpenPosition(): Boolean {


        return positionTracker.hasPosition()


    }









    fun openPosition(


        position: OpenBacktestPosition


    ) {



        positionTracker.open(



            TradePosition(


                positionId = UUID.randomUUID().toString(),


                action = position.action,


                entryPrice = position.entryPrice,


                stopLoss = position.stopLoss,


                takeProfit = position.takeProfit,


                positionSize = position.positionSize,


                openedAt = position.openedAt,



                learningContext = position.learningContext


            )


        )


    }









    fun evaluateOutcome(


        candle: MarketCandle


    ): TradeOutcome? {



        val outcome =


            positionTracker.evaluate(candle)





        lastOutcome = outcome





        return outcome


    }









    fun evaluateCandle(


        candle: MarketCandle


    ): BacktestTrade? {



        val outcome =


            evaluateOutcome(candle)





        outcome ?: return null







        val trade = BacktestTrade(



            action = outcome.action,



            entryPrice = outcome.entryPrice,



            exitPrice = outcome.exitPrice,



            stopLoss = outcome.stopLoss,



            takeProfit = outcome.takeProfit,



            positionSize = outcome.positionSize,



            profitLoss = outcome.profitLoss,



            openedAt = outcome.openedAt,



            closedAt = outcome.closedAt,



            success = outcome.success,



            /**
             * انتقال Intelligence Snapshot
             *
             * Trade Outcome
             *        |
             *        v
             * BacktestTrade
             *        |
             *        v
             * Intelligence Memory
             */
            learningContext = outcome.learningContext


        )







        completedTrades.add(trade)







        return trade


    }









    fun getLastOutcome(): TradeOutcome? {



        return lastOutcome


    }









    fun getCompletedTrades(): List<BacktestTrade> {



        return completedTrades.toList()


    }









    fun reset() {



        positionTracker.reset()



        completedTrades.clear()



        lastOutcome = null


    }


}