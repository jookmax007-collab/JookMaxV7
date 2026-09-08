package com.jookmax.v7.brain.decision


import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class ConfidenceEngine @Inject constructor() {



    fun calculate(

        decisionScore: DecisionScore

    ): Double {



        val baseConfidence =

            when (decisionScore.direction) {


                DecisionDirection.BULLISH ->

                    decisionScore.score



                DecisionDirection.BEARISH ->

                    1.0 - decisionScore.score



                DecisionDirection.NEUTRAL ->

                    0.5

            }





        val riskAdjustment =

            if (decisionScore.riskApproved)

                1.0

            else

                0.5





        return (

            baseConfidence *

            riskAdjustment

        )

            .coerceIn(0.0, 1.0)

    }


}