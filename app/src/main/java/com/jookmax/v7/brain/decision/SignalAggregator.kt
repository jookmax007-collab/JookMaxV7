package com.jookmax.v7.brain.decision


import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class SignalAggregator @Inject constructor() {



    fun aggregate(

        marketScore: Double,

        riskAllowed: Boolean,

        learningReward: Double

    ): AggregatedSignal {



        val safeMarketScore =

            marketScore.coerceIn(0.0, 1.0)





        val safeLearningReward =

            learningReward.coerceIn(-1.0, 1.0)





        val learningScore =

            (safeLearningReward + 1.0) / 2.0





        val riskScore =

            if (riskAllowed)

                1.0

            else

                0.0





        val finalScore =

            (

                safeMarketScore * 0.5

                +

                riskScore * 0.3

                +

                learningScore * 0.2

            )

                .coerceIn(0.0, 1.0)





        return AggregatedSignal(

            score = finalScore,

            riskApproved = riskAllowed

        )

    }


}



data class AggregatedSignal(

    val score: Double,

    val riskApproved: Boolean

)
