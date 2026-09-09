package com.jookmax.v7.brain.intelligence


import com.jookmax.v7.brain.decision.DecisionAction

import javax.inject.Inject
import javax.inject.Singleton



/**
 * Decision Memory
 *
 * Stores historical intelligence decisions.
 *
 * Flow:
 *
 * IntelligenceDecision
 *          |
 *          v
 * DecisionMemory
 *          |
 *          v
 * Intelligence Analytics
 *
 */
@Singleton
class DecisionMemory @Inject constructor() {


    private val decisions =

        mutableListOf<DecisionExperience>()



    fun add(

        experience: DecisionExperience

    ) {

        decisions.add(

            experience

        )

    }



    fun getAll():

            List<DecisionExperience> {

        return decisions.toList()

    }



    fun getLatest():

            DecisionExperience? {

        return decisions.lastOrNull()

    }



    fun size():

            Int {

        return decisions.size

    }



    fun clear() {

        decisions.clear()

    }



    fun calculateAccuracy():

            Double {


        if (decisions.isEmpty())

            return 0.0



        val correct =

            decisions.count {

                it.success

            }



        return correct.toDouble() /

                decisions.size.toDouble()

    }



    fun calculateAverageConfidence():

            Double {


        if (decisions.isEmpty())

            return 0.0



        return decisions

            .map {

                it.confidence

            }

            .average()

    }



    fun calculateAverageReward():

            Double {


        if (decisions.isEmpty())

            return 0.0



        return decisions

            .map {

                it.reward

            }

            .average()

    }



    fun calculateIntelligenceScore():

            Double {


        val accuracy =

            calculateAccuracy()



        val confidence =

            calculateAverageConfidence()



        val reward =

            calculateAverageReward()



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



    private fun normalizeReward(

        reward: Double

    ): Double {


        return when {

            reward <= 0 -> 0.0

            reward >= 1 -> 1.0

            else -> reward

        }

    }

}



data class DecisionExperience(


    val action: DecisionAction,


    val confidence: Double,


    val reward: Double,


    val success: Boolean,


    val timestamp: Long =

        System.currentTimeMillis()

)