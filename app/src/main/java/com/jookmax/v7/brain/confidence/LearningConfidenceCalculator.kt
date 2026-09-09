package com.jookmax.v7.brain.confidence


import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class LearningConfidenceCalculator @Inject constructor(){



    fun calculate(

        reward: Double

    ): Double {



        return when {


            reward > 0.5 ->

                1.0



            reward >= 0 ->

                0.5



            else ->

                0.2


        }

    }

}
