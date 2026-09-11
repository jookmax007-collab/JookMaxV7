package com.jookmax.v7.brain.intelligence


import com.jookmax.v7.brain.intelligence.memory.DecisionPattern

import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class IntelligenceMemoryScorer @Inject constructor() {



    fun calculateAdjustment(

        memories: List<DecisionPattern>

    ): Double {



        if (memories.isEmpty()) {

            return 1.0

        }



        val successRate =

            memories.count {

                it.reward > 0

            }.toDouble() /

            memories.size.toDouble()



        val averageReward =

            memories

                .map {

                    normalizeReward(it.reward)

                }

                .average()



        val score =

            (

                    successRate * 0.6 +

                    averageReward * 0.4

                    )




        return when {


            score >= 0.75 ->

                1.08



            score >= 0.55 ->

                1.04



            score <= 0.30 ->

                0.92



            else ->

                1.0

        }

    }





    private fun normalizeReward(

        reward: Double

    ): Double {


        return when {


            reward <= -1 ->

                0.0



            reward >= 1 ->

                1.0



            else ->

                (reward + 1) / 2

        }

    }


}