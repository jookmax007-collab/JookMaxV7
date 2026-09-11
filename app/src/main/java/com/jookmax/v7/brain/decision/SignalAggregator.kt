package com.jookmax.v7.brain.decision


import com.jookmax.v7.liquidity.model.LiquidityBias

import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class SignalAggregator @Inject constructor() {



    fun aggregate(


        marketScore: Double,


        riskAllowed: Boolean,


        learningReward: Double,


        liquidityScore: Double,


        liquidityBias: LiquidityBias


    ): AggregatedSignal {



        val safeMarketScore =

            marketScore.coerceIn(0.0, 1.0)





        val safeLearningReward =

            learningReward.coerceIn(-1.0, 1.0)





        val learningScore =

            (safeLearningReward + 1.0) / 2.0





        val safeLiquidityScore =

            liquidityScore.coerceIn(0.0, 1.0)





        val riskScore =

            if (riskAllowed)

                1.0

            else

                0.0





        val finalScore =

            (

                safeMarketScore * 0.40

                +

                riskScore * 0.25

                +

                learningScore * 0.15

                +

                safeLiquidityScore * 0.20

            )

                .coerceIn(0.0, 1.0)





        return AggregatedSignal(

            score = finalScore,

            riskApproved = riskAllowed,

            liquidityBias = liquidityBias

        )

    }


}



data class AggregatedSignal(


    val score: Double,


    val riskApproved: Boolean,


    val liquidityBias: LiquidityBias


)