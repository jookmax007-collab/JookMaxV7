package com.jookmax.v7.brain.confidence


import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MarketConfidenceCalculator @Inject constructor(){



    fun calculate(

        marketScore: Double

    ): Double {


        return when {


            marketScore >= 0.8 ->

                1.0



            marketScore >= 0.6 ->

                0.75



            marketScore <= 0.3 ->

                0.75



            else ->

                0.5

        }

    }

}