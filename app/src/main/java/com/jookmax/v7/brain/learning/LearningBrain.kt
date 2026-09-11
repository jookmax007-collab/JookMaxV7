package com.jookmax.v7.brain.learning


import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class LearningBrain @Inject constructor(

    private val experienceManager: LearningExperienceManager

) {



    private var learningRuns: Int = 0


    private var lastResult: LearningResult? = null



    fun learn(

        success: Boolean,

        reward: Double

    ): LearningResult {



        learningRuns++


        val adjustedReward =

            calculateAdjustedReward(

                reward

            )



        val result = LearningResult(

            success = success,

            reward = adjustedReward,

            runNumber = learningRuns

        )



        lastResult = result


        return result

    }





    fun addExperience(

        experience: LearningExperience

    ) {


        experienceManager.addExperience(

            experience

        )

    }







    private fun calculateAdjustedReward(

        reward: Double

    ): Double {


        val historicalReward =

            experienceManager

                .getAverageReward()



        return (

                reward * 0.7 +

                historicalReward * 0.3

                )

    }







    fun getLearningRuns(): Int {

        return learningRuns

    }






    fun getLastResult(): LearningResult? {

        return lastResult

    }







    fun getLearningMemorySize(): Int {


        return experienceManager

            .getExperienceCount()

    }






    fun getAverageReward(): Double {


        return experienceManager

            .getAverageReward()

    }







    fun reset() {


        learningRuns = 0


        lastResult = null


        experienceManager.clear()

    }


}





data class LearningResult(

    val success: Boolean,

    val reward: Double,

    val runNumber: Int

)