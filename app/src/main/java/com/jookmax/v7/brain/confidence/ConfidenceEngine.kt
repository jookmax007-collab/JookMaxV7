package com.jookmax.v7.brain.confidence


import com.jookmax.v7.brain.risk.RiskDecision
import com.jookmax.v7.liquidity.model.LiquidityContext

import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class ConfidenceEngine @Inject constructor(


    private val marketConfidenceCalculator: MarketConfidenceCalculator,


    private val riskConfidenceCalculator: RiskConfidenceCalculator,


    private val learningConfidenceCalculator: LearningConfidenceCalculator,


    private val liquidityConfidenceCalculator: LiquidityConfidenceCalculator,


    private val confidenceFusionEngine: ConfidenceFusionEngine


) {



    fun calculate(


        decisionScore: com.jookmax.v7.brain.decision.DecisionScore,


        marketScore: Double,


        riskDecision: RiskDecision,


        learningReward: Double,


        liquidityContext: LiquidityContext = LiquidityContext()


    ): Double {



        val marketConfidence =

            marketConfidenceCalculator.calculate(

                marketScore

            )





        val riskConfidence =

            riskConfidenceCalculator.calculate(

                riskDecision

            )





        val learningConfidence =

            learningConfidenceCalculator.calculate(

                learningReward

            )





        val liquidityConfidence =

            liquidityConfidenceCalculator.calculate(

                liquidityContext

            )





        val fusionResult =

            confidenceFusionEngine.fuse(

                marketConfidence = marketConfidence,

                riskConfidence = riskConfidence,

                learningConfidence = learningConfidence,

                liquidityConfidence = liquidityConfidence

            )





        val decisionAdjustment =


            when(decisionScore.direction) {


                com.jookmax.v7.brain.decision.DecisionDirection.BULLISH ->

                    decisionScore.score



                com.jookmax.v7.brain.decision.DecisionDirection.BEARISH ->

                    1.0 - decisionScore.score



                com.jookmax.v7.brain.decision.DecisionDirection.NEUTRAL ->

                    0.5

            }





        return (

            fusionResult.finalConfidence *

            decisionAdjustment

        )

            .coerceIn(0.0,1.0)

    }


}
