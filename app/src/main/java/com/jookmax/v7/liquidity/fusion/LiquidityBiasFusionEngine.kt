package com.jookmax.v7.liquidity.fusion


import com.jookmax.v7.liquidity.model.LiquidityBias

import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class LiquidityBiasFusionEngine @Inject constructor() {



    fun fuse(

        liquidityScore: Double,

        liquidityBias: LiquidityBias

    ): Double {



        val biasAdjustment =

            when(liquidityBias) {



                LiquidityBias.BULLISH ->

                    0.15



                LiquidityBias.BEARISH ->

                    -0.15



                LiquidityBias.NEUTRAL ->

                    0.0

            }





        return (

            liquidityScore +

            biasAdjustment

        )

            .coerceIn(-1.0, 1.0)

    }


}