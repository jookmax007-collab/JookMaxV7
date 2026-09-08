package com.jookmax.v7.brain.confidence


import javax.inject.Inject
import javax.inject.Singleton



/**
 * Combines multiple confidence sources
 *
 * Sources:
 *
 * Market Confidence
 * Risk Confidence
 * Learning Confidence
 *
 * Output:
 *
 * Final Intelligence Confidence
 *
 */
@Singleton
class ConfidenceFusionEngine @Inject constructor() {



    fun fuse(

        marketConfidence: Double,

        riskConfidence: Double,

        learningConfidence: Double

    ): ConfidenceModel {



        val finalConfidence =


            (

                marketConfidence * MARKET_WEIGHT

                +

                riskConfidence * RISK_WEIGHT

                +

                learningConfidence * LEARNING_WEIGHT

            )

                .coerceIn(0.0, 1.0)





        return ConfidenceModel(

            marketConfidence = marketConfidence,

            riskConfidence = riskConfidence,

            learningConfidence = learningConfidence,

            finalConfidence = finalConfidence

        )

    }





    companion object {



        private const val MARKET_WEIGHT = 0.40


        private const val RISK_WEIGHT = 0.35


        private const val LEARNING_WEIGHT = 0.25


    }


}