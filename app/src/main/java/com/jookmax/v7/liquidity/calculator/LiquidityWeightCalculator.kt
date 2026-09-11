package com.jookmax.v7.liquidity.calculator


import com.jookmax.v7.liquidity.model.LiquidityEvent

import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class LiquidityWeightCalculator @Inject constructor() {



    fun calculate(

        events: List<LiquidityEvent>

    ): Double {



        if (events.isEmpty())

            return 0.0





        var score = 0.0





        events.forEach { event ->


            score += when(event) {



                LiquidityEvent.LIQUIDITY_SWEEP ->

                    0.10



                LiquidityEvent.STOP_HUNT ->

                    0.15



                LiquidityEvent.FAKE_BREAKOUT ->

                    -0.15



                LiquidityEvent.EQUAL_HIGH_FOUND ->

                    0.05



                LiquidityEvent.EQUAL_LOW_FOUND ->

                    0.05



                LiquidityEvent.NONE ->

                    0.0

            }

        }





        return score.coerceIn(-1.0, 1.0)

    }


}