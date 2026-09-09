package com.jookmax.v7.brain.backtest.analytics


import com.jookmax.v7.brain.backtest.model.BacktestTrade

import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class BacktestAnalytics @Inject constructor() {



    fun analyze(

        trades: List<BacktestTrade>

    ): BacktestMetrics {


        if (trades.isEmpty()) {

            return BacktestMetrics(

                totalTrades = 0,

                winRate = 0.0,

                profitFactor = 0.0,

                averageWin = 0.0,

                averageLoss = 0.0,

                expectancy = 0.0,

                maxDrawdown = 0.0

            )

        }



        val winningTrades =

            trades.filter {

                it.profitLoss > 0.0

            }



        val losingTrades =

            trades.filter {

                it.profitLoss < 0.0

            }



        val totalProfit =

            winningTrades.sumOf {

                it.profitLoss

            }



        val totalLoss =

            kotlin.math.abs(

                losingTrades.sumOf {

                    it.profitLoss

                }

            )



        val averageWin =

            if (winningTrades.isEmpty())

                0.0

            else

                totalProfit / winningTrades.size



        val averageLoss =

            if (losingTrades.isEmpty())

                0.0

            else

                totalLoss / losingTrades.size



        val winRate =

            winningTrades.size.toDouble() /

                    trades.size.toDouble()



        val profitFactor =

            if (totalLoss == 0.0)

                0.0

            else

                totalProfit / totalLoss



        val expectancy =

            (winRate * averageWin) -

                    ((1 - winRate) * averageLoss)



        val maxDrawdown =

            calculateMaxDrawdown(trades)



        return BacktestMetrics(

            totalTrades = trades.size,

            winRate = winRate,

            profitFactor = profitFactor,

            averageWin = averageWin,

            averageLoss = averageLoss,

            expectancy = expectancy,

            maxDrawdown = maxDrawdown

        )

    }



    private fun calculateMaxDrawdown(

        trades: List<BacktestTrade>

    ): Double {


        var balance = 0.0

        var peak = 0.0

        var maxDrawdown = 0.0



        trades.forEach { trade ->


            balance += trade.profitLoss



            if (balance > peak) {

                peak = balance

            }



            val drawdown =

                peak - balance



            if (drawdown > maxDrawdown) {

                maxDrawdown = drawdown

            }

        }



        return maxDrawdown

    }


}
