package com.jookmax.v7.brain.decision


import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class DecisionEngine @Inject constructor(

    private val signalAggregator: SignalAggregator,

    private val decisionScoreCalculator: DecisionScoreCalculator,

    private val confidenceEngine: ConfidenceEngine

) {



    fun decide(

        context: DecisionContext

    ): DecisionResult {



        val riskAllowed =

            context.riskDecision.positionSize > 0.0




        val aggregatedSignal =

            signalAggregator.aggregate(

                marketScore = context.marketScore,

                riskAllowed = riskAllowed,

                learningReward = context.learningReward

            )




        val decisionScore =

            decisionScoreCalculator.calculate(

                aggregatedSignal

            )




        val confidence =

            confidenceEngine.calculate(

                decisionScore

            )




        if (!decisionScore.riskApproved) {


            return DecisionResult(

                action = DecisionAction.HOLD,

                confidence = confidence

            )

        }




        return when(decisionScore.direction) {


            DecisionDirection.BULLISH ->

                DecisionResult(

                    action = DecisionAction.BUY,

                    confidence = confidence

                )



            DecisionDirection.BEARISH ->

                DecisionResult(

                    action = DecisionAction.SELL,

                    confidence = confidence

                )



            DecisionDirection.NEUTRAL ->

                DecisionResult(

                    action = DecisionAction.HOLD,

                    confidence = confidence

                )

        }

    }



    fun reset() {

    }


}



enum class DecisionAction {

    BUY,

    SELL,

    HOLD

}



data class DecisionResult(

    val action: DecisionAction,

    val confidence: Double

)