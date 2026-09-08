package com.jookmax.v7.brain.risk


import javax.inject.Inject
import javax.inject.Singleton



/**
 * Calculates stop loss price.
 *
 * Uses market volatility / ATR value.
 *
 * For BUY:
 * stop loss = entry - volatility * multiplier
 *
 * For SELL:
 * stop loss = entry + volatility * multiplier
 */
@Singleton
class StopLossCalculator @Inject constructor() {



    fun calculate(

        entryPrice: Double,

        volatility: Double,

        isLong: Boolean,

        multiplier: Double = 2.0

    ): Double {



        val distance =

            volatility * multiplier



        return if (isLong) {

            entryPrice - distance

        } else {

            entryPrice + distance

        }

    }


}