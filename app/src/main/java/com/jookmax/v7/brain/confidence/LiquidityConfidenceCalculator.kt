package com.jookmax.v7.brain.confidence


import com.jookmax.v7.liquidity.model.LiquidityBias
import com.jookmax.v7.liquidity.model.LiquidityContext

import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class LiquidityConfidenceCalculator @Inject constructor() {



    fun calculate(

        liquidityContext: LiquidityContext

    ): Double {



        var confidence = 0.5





        if (liquidityContext.hasSweep) {

            confidence += 0.10

        }





        if (liquidityContext.hasStopHunt) {

            confidence += 0.10

        }





        if (liquidityContext.hasFakeBreakout) {

            confidence -= 0.15

        }





        confidence += when(liquidityContext.liquidityBias) {


            LiquidityBias.BULLISH ->

                0.10



            LiquidityBias.BEARISH ->

                -0.10



            LiquidityBias.NEUTRAL ->

                0.0

        }





        return confidence.coerceIn(0.0,1.0)

    }


}
