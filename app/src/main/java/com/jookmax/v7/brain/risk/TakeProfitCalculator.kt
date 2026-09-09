package com.jookmax.v7.brain.risk


import javax.inject.Inject
import javax.inject.Singleton



/**
 * Calculates take profit price.
 *
 * BUY:
 * TP = entry + riskDistance * rewardRatio
 *
 * SELL:
 * TP = entry - riskDistance * rewardRatio
 */
@Singleton
class TakeProfitCalculator @Inject constructor() {



    fun calculate(

        entryPrice: Double,

        stopLossPrice: Double,

        isLong: Boolean,

        rewardRatio: Double = 2.0

    ): Double {



        val riskDistance =

            kotlin.math.abs(
                entryPrice - stopLossPrice
            )



        return if (isLong) {

            entryPrice + (riskDistance * rewardRatio)

        } else {

            entryPrice - (riskDistance * rewardRatio)

        }

    }


}
