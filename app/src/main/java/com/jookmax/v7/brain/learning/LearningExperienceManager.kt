package com.jookmax.v7.brain.learning


import javax.inject.Inject
import javax.inject.Singleton



/**
 * Learning Experience Manager
 *
 * Responsible for:
 *
 * - storing past brain experiences
 * - retrieving learning history
 * - calculating memory statistics
 * - resetting learning memory
 *
 *
 * Flow:
 *
 * LearningExperience
 *          |
 *          v
 * LearningExperienceManager
 *          |
 *          v
 * LearningBrain
 *
 */
@Singleton
class LearningExperienceManager @Inject constructor() {



    private val experiences =

        mutableListOf<LearningExperience>()





    fun addExperience(

        experience: LearningExperience

    ) {


        experiences.add(

            experience

        )


    }







    fun getExperiences():

            List<LearningExperience> {


        return experiences.toList()

    }







    fun getLatestExperience():

            LearningExperience? {


        return experiences.lastOrNull()

    }







    fun getExperienceCount():

            Int {


        return experiences.size

    }







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







    fun getSuccessRate():

            Double {


        if (experiences.isEmpty()) {

            return 0.0

        }





        val successCount =

            experiences

                .count {

                    it.success

                }





        return successCount.toDouble() /

                experiences.size.toDouble()

    }







    fun clear() {


        experiences.clear()

    }


}