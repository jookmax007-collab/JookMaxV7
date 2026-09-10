package com.jookmax.v7.brain.intelligence


import com.jookmax.v7.domain.repository.PersistentDecisionMemoryRepository

import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class IntelligenceMemoryAnalyzer @Inject constructor(

    private val repository: PersistentDecisionMemoryRepository

) {



    suspend fun calculateMemoryScore(): Double {


        val memories = repository.getAll()


        if (memories.isEmpty()) {

            return 0.0

        }



        val accuracy =

            memories.count {

                it.reward > 0

            }.toDouble() /

            memories.size.toDouble()



        val confidence =

            memories

                .map {

                    it.confidence

                }

                .average()



        val reward =

            memories

                .map {

                    it.reward

                }

                .average()



        return (

                accuracy * 0.5 +

                confidence * 0.3 +

                normalizeReward(reward) * 0.2

                )

            .coerceIn(

                0.0,

                1.0

            )

    }




    suspend fun calculateAdjustment(): Double {


        val score = calculateMemoryScore()



        return when {


            score > 0.75 ->

                1.05



            score < 0.45 ->

                0.95



            else ->

                1.0

        }

    }





    suspend fun getMemorySize(): Int {


        return repository.count()

    }





    private fun normalizeReward(

        reward: Double

    ): Double {


        return when {


            reward <= 0 ->

                0.0



            reward >= 1 ->

                1.0



            else ->

                reward

        }

    }


}
