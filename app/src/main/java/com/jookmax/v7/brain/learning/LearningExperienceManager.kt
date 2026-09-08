package com.jookmax.v7.brain.learning


import javax.inject.Inject
import javax.inject.Singleton



/**
 * Learning Experience Manager
 *
 * Responsible for:
 *
 * - storing brain experiences
 * - keeping learning memory
 * - providing historical statistics
 * - supporting adaptive learning
 * - resetting memory
 *
 *
 * Flow:
 *
 * Brain Decision
 *        |
 *        v
 * LearningExperience
 *        |
 *        v
 * LearningExperienceManager
 *        |
 *        v
 * LearningBrain
 *
 */
@Singleton
class LearningExperienceManager @Inject constructor() {



    private val experiences =

        mutableListOf<LearningExperience>()






    /**
     * Add new learning experience
     */
    fun addExperience(

        experience: LearningExperience

    ) {


        experiences.add(

            experience

        )

    }








    /**
     * Return all stored experiences
     */
    fun getExperiences():

            List<LearningExperience> {


        return experiences.toList()

    }








    /**
     * Return latest brain experience
     */
    fun getLatestExperience():

            LearningExperience? {


        return experiences.lastOrNull()

    }








    /**
     * Total memory size
     */
    fun getExperienceCount():

            Int {


        return experiences.size

    }








    /**
     * Calculate average reward
     */
    fun getAverageReward():

            Double {


        if (experiences.isEmpty()) {

            return 0.0

        }




        return experiences

            .map {

                it.reward

            }

            .average()

    }








    /**
     * Calculate success percentage
     */
    fun getSuccessRate():

            Double {


        if (experiences.isEmpty()) {

            return 0.0

        }




        val successfulTrades =

            experiences

                .count {

                    it.success

                }




        return successfulTrades.toDouble() /

                experiences.size.toDouble()

    }








    /**
     * Calculate failure percentage
     */
    fun getFailureRate():

            Double {


        return 1.0 -

                getSuccessRate()

    }








    /**
     * Clear all learning memory
     */
    fun clear() {


        experiences.clear()

    }








    /**
     * Reset learning memory
     */
    fun reset() {


        clear()

    }


}