package com.jookmax.v7.brain.decision


import com.jookmax.v7.brain.confidence.ConfidenceFusionEngine
import com.jookmax.v7.brain.confidence.LearningConfidenceCalculator
import com.jookmax.v7.brain.confidence.MarketConfidenceCalculator
import com.jookmax.v7.brain.confidence.RiskConfidenceCalculator
import com.jookmax.v7.brain.risk.RiskDecision

import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class ConfidenceEngine @Inject constructor(


    private val marketConfidenceCalculator: MarketConfidenceCalculator,


    private val riskConfidenceCalculator: RiskConfidenceCalculator,


    private val learningConfidenceCalculator: LearningConfidenceCalculator,


    private val confidenceFusionEngine: ConfidenceFusionEngine


) {



    fun calculate(


        decisionScore: DecisionScore,


        marketScore: Double,


        riskDecision: RiskDecision,


        learningReward: Double


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





        val fusionResult =

            confidenceFusionEngine.fuse(

                marketConfidence = marketConfidence,

                riskConfidence = riskConfidence,

                learningConfidence = learningConfidence

            )





        val decisionAdjustment =


            when(decisionScore.direction) {



                DecisionDirection.BULLISH ->

                    decisionScore.score



                DecisionDirection.BEARISH ->

                    1.0 - decisionScore.score



                DecisionDirection.NEUTRAL ->

                    0.5

            }





        return (

            fusionResult.finalConfidence *

            decisionAdjustment

        )

            .coerceIn(0.0,1.0)

    }


}