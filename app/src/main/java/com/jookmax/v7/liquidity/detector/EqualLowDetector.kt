package com.jookmax.v7.liquidity.detector


import com.jookmax.v7.core.model.MarketCandle

import javax.inject.Inject
import javax.inject.Singleton
import com.jookmax.v7.liquidity.model.LiquidityType

import com.jookmax.v7.liquidity.model.LiquidityZone




@Singleton
class EqualLowDetector @Inject constructor() {


    fun detect(

        candles: List<MarketCandle>,

        tolerance: Double = 0.002

    ): LiquidityZone? {


        if (candles.size < 3)

            return null





        val latestLow =

            candles.last().low





        val previousLow =

            candles

                .dropLast(1)

                .minOfOrNull {

                    it.low

                }

                ?: return null





        val difference =

            kotlin.math.abs(
                latestLow - previousLow
            ) / previousLow





        if (difference <= tolerance) {


            return LiquidityZone(


                type = LiquidityType.EQUAL_LOW,


                highPrice = maxOf(

                    latestLow,

                    previousLow

                ),


                lowPrice = minOf(

                    latestLow,

                    previousLow

                ),


                strength = 0.7,


                touched = false,


                swept = false,


                timestamp = candles.last().timestamp


            )

        }





        return null

    }


}
