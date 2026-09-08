package com.jookmax.v7.brain.intelligence.feedback


import com.jookmax.v7.brain.intelligence.IntelligenceDecision

import javax.inject.Inject
import javax.inject.Singleton



/**
 * Intelligence Feedback Bridge
 *
 * Connects intelligence decisions
 * with feedback memory.
 *
 * Flow:
 *
 * IntelligenceDecision
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

        decision: IntelligenceDecision,

        reward: Double

    ) {


        val feedback =

            IntelligenceFeedback(


                action = decision.action,


                confidence = decision.confidence,


                reward = reward,


                success = reward > 0.0


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