package com.jookmax.v7.brain.confidence


import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class ConfidenceFusionEngine @Inject constructor(

    private val feedbackManager: ConfidenceFeedbackManager,

    private val confidenceAnalytics: ConfidenceAnalytics

) {



    fun fuse(

        marketConfidence: Double,

        riskConfidence: Double,

        learningConfidence: Double

    ): ConfidenceModel {



        val baseConfidence =

            (

                marketConfidence * MARKET_WEIGHT

                +

                riskConfidence * RISK_WEIGHT

                +

                learningConfidence * LEARNING_WEIGHT

            )

                .coerceIn(0.0, 1.0)





        val history =

            feedbackManager

                .getHistory()





        val adjustment =

            confidenceAnalytics

                .calculateAdjustment(

                    history

                )





        val finalConfidence =

            (

                baseConfidence *

                adjustment

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
