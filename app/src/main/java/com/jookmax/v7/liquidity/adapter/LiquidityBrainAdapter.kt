package com.jookmax.v7.liquidity.adapter


import com.jookmax.v7.liquidity.model.LiquidityBias
import com.jookmax.v7.liquidity.model.LiquidityContext

import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class LiquidityBrainAdapter @Inject constructor() {



    fun calculateLiquidityScore(

        context: LiquidityContext

    ): Double {



        var score = context.liquidityScore





        if (context.hasSweep) {

            score += 0.15

        }





        if (context.hasStopHunt) {

            score += 0.10

        }





        if (context.hasFakeBreakout) {

            score -= 0.10

        }





        return score.coerceIn(

            0.0,

            1.0

        )

    }







    fun getBias(

        context: LiquidityContext

    ): LiquidityBias {



        return context.liquidityBias

    }







    fun isLiquidityConfirmed(

        context: LiquidityContext

    ): Boolean {



        return (

            context.liquidityScore >= 0.6 ||

            context.hasSweep ||

            context.hasStopHunt

        )

    }


}