package com.jookmax.v7.brain.confidence


import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class ConfidenceFeedbackManager @Inject constructor() {



    private var history = ConfidenceHistory()





    fun record(

        feedback: ConfidenceFeedback

    ) {


        history =

            history.add(feedback)

    }







    fun getHistory(): ConfidenceHistory {


        return history

    }







    fun calculateAdjustment(): Double {


        val reward =

            history.averageReward()



        return when {


            reward > 0.5 ->

                1.05





            reward < -0.5 ->

                0.95





            else ->

                1.0

        }

    }







    fun clear() {


        history = ConfidenceHistory()

    }


}
