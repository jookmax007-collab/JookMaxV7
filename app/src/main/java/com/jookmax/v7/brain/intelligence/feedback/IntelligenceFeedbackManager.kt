package com.jookmax.v7.brain.intelligence.feedback


import javax.inject.Inject
import javax.inject.Singleton



/**
 * Intelligence Feedback Manager
 *
 * Keeps intelligence feedback history.
 *
 */
@Singleton
class IntelligenceFeedbackManager @Inject constructor() {



    private val feedbacks =

        mutableListOf<IntelligenceFeedback>()





    fun add(

        feedback: IntelligenceFeedback

    ) {


        feedbacks.add(

            feedback

        )

    }







    fun getAll():

            List<IntelligenceFeedback> {


        return feedbacks.toList()

    }







    fun getLatest():

            IntelligenceFeedback? {


        return feedbacks.lastOrNull()

    }







    fun size():

            Int {


        return feedbacks.size

    }







    fun clear() {


        feedbacks.clear()

    }







    fun averageReward():

            Double {


        if (feedbacks.isEmpty())

            return 0.0





        return feedbacks

            .map {

                it.reward

            }

            .average()

    }







    fun successRate():

            Double {


        if (feedbacks.isEmpty())

            return 0.0





        val successCount =

            feedbacks.count {

                it.success

            }





        return successCount.toDouble() /

                feedbacks.size.toDouble()

    }


}
