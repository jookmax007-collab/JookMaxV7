package com.jookmax.v7.brain.intelligence.feedback


import com.jookmax.v7.brain.intelligence.validation.ValidatedDecision

import javax.inject.Inject
import javax.inject.Singleton



/**
 * Intelligence Feedback Bridge
 *
 * Connects validated intelligence decisions
 * with feedback memory.
 *
 * Flow:
 *
 * ValidatedDecision
 *          |
 *          v
 * IntelligenceFeedback
 *          |
 *          v
 * IntelligenceFeedbackManager
 *
 */
@Singleton
class IntelligenceFeedbackBridge @Inject constructor(


    private val feedbackManager: IntelligenceFeedbackManager


) {



    fun recordDecision(

        decision: ValidatedDecision,

        reward: Double

    ) {


        val feedback =

            IntelligenceFeedback(


                action = decision.finalAction,


                confidence = decision.validationScore,


                reward = reward,


                success =

                    decision.approved && reward > 0.0


            )





        feedbackManager.add(

            feedback

        )

    }







    fun getFeedbackCount():

            Int {


        return feedbackManager.size()

    }







    fun getAverageReward():

            Double {


        return feedbackManager.averageReward()

    }







    fun getSuccessRate():

            Double {


        return feedbackManager.successRate()

    }


}