package com.jookmax.v7.liquidity.detector


import com.jookmax.v7.core.model.MarketCandle

import javax.inject.Inject
import javax.inject.Singleton
import com.jookmax.v7.liquidity.model.LiquidityEvent




@Singleton
class LiquiditySweepDetector @Inject constructor() {



    fun detect(

        candles: List<MarketCandle>,

        liquidityPrice: Double,

        tolerance: Double = 0.002

    ): LiquidityEvent {



        if (candles.size < 2)

            return LiquidityEvent.NONE





        val latest =

            candles.last()





        val previous =

            candles[candles.size - 2]





        val upperSweep =

            latest.high > liquidityPrice &&

            latest.close < liquidityPrice





        val lowerSweep =

            latest.low < liquidityPrice &&

            latest.close > liquidityPrice





        return when {


            upperSweep &&

                    kotlin.math.abs(

                        latest.high - liquidityPrice

                    ) / liquidityPrice <= tolerance ->


                LiquidityEvent.LIQUIDITY_SWEEP





            lowerSweep &&

                    kotlin.math.abs(

                        liquidityPrice - latest.low

                    ) / liquidityPrice <= tolerance ->


                LiquidityEvent.LIQUIDITY_SWEEP





            else ->

                LiquidityEvent.NONE

        }


    }


}
