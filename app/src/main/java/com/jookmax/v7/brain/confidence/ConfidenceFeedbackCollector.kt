package com.jookmax.v7.brain.confidence


import com.jookmax.v7.brain.decision.DecisionResult

import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class ConfidenceFeedbackCollector @Inject constructor(

    private val feedbackManager: ConfidenceFeedbackManager

) {



    fun collect(

        decisionResult: DecisionResult,

        reward: Double

    ) {



        val feedback =

            ConfidenceFeedback(


                decision =

                    decisionResult.action.name,



                initialConfidence =

                    decisionResult.confidence,



                reward = reward,



                success =

                    reward > 0.0



            )







        feedbackManager.record(

            feedback

        )

    }







    fun clear() {


        feedbackManager.clear()

    }


}