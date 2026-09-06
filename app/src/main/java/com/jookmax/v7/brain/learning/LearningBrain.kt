package com.jookmax.v7.brain.learning


class LearningBrain {


    private var learningRuns: Int = 0

    private var lastResult: LearningResult? = null



    fun learn(
        success: Boolean,
        reward: Double
    ): LearningResult {


        learningRuns++


        val result = LearningResult(

            success = success,

            reward = reward,

            runNumber = learningRuns

        )


        lastResult = result


        return result

    }



    fun getLearningRuns(): Int {

        return learningRuns

    }



    fun getLastResult(): LearningResult? {

        return lastResult

    }



    fun reset() {

        learningRuns = 0

        lastResult = null

    }

}




data class LearningResult(

    val success: Boolean,

    val reward: Double,

    val runNumber: Int

)