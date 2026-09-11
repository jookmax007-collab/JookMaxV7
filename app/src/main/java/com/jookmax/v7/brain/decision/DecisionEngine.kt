package com.jookmax.v7.brain.decision


import com.jookmax.v7.brain.confidence.ConfidenceEngine
import com.jookmax.v7.brain.risk.RiskDecision
import com.jookmax.v7.liquidity.model.LiquidityBias
import com.jookmax.v7.liquidity.model.LiquidityContext

import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class DecisionEngine @Inject constructor(

    private val signalAggregator: SignalAggregator,

    private val decisionScoreCalculator: DecisionScoreCalculator,

    private val confidenceEngine: ConfidenceEngine

) {



    fun decide(

        marketScore: Double,

        riskAllowed: Boolean,

        learningReward: Double,

        liquidityScore: Double,

        liquidityBias: LiquidityBias,

        liquidityContext: LiquidityContext,

        riskDecision: RiskDecision

    ): DecisionResult {



        val aggregatedSignal =

            signalAggregator.aggregate(

                marketScore = marketScore,

                riskAllowed = riskAllowed,

                learningReward = learningReward,

                liquidityScore = liquidityScore,

                liquidityBias = liquidityBias

            )





        val decisionScore =

            decisionScoreCalculator.calculate(

                aggregatedSignal

            )





        val confidence =

            confidenceEngine.calculate(

                decisionScore = decisionScore,

                marketScore = marketScore,

                riskDecision = riskDecision,

                learningReward = learningReward,

                liquidityContext = liquidityContext

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