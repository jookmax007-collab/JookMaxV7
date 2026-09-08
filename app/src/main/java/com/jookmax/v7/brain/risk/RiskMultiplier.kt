package com.jookmax.v7.brain.risk


/**
 * Calculates risk multiplier based on market volatility.
 *
 * Lower multiplier means lower exposure.
 */
class RiskMultiplier {


    fun calculate(

        volatility: Double

    ): Double {


        return when {


            volatility <= 0.5 ->

                1.0



            volatility <= 1.5 ->

                0.75



            volatility <= 3.0 ->

                0.5



            else ->

                0.0

        }

    }


}