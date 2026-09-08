package com.jookmax.v7.brain.intelligence


import com.jookmax.v7.brain.decision.DecisionAction
import com.jookmax.v7.brain.decision.DecisionResult

import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class IntelligenceEngine @Inject constructor(


    private val advisor: IntelligenceAdvisor


) {



    fun evaluate(

        decisionResult: DecisionResult

    ): IntelligenceDecision {



        val adjustment =

            advisor.calculateAdjustment()





        val confidence =

            (

                decisionResult.confidence *

                adjustment

            )

                .coerceIn(0.0,1.0)







        val reason =

            when(decisionResult.action) {


                DecisionAction.BUY ->

                    "Bullish intelligence alignment"


                DecisionAction.SELL ->

                    "Bearish intelligence alignment"


                DecisionAction.HOLD ->

                    "Insufficient intelligence confidence"

            }







        return IntelligenceDecision(


            action = decisionResult.action,


            confidence = confidence,


            reason = reason


        )

    }


}