package com.jookmax.v7.liquidity.detector


import com.jookmax.v7.core.model.MarketCandle
import com.jookmax.v7.liquidity.model.LiquidityType
import com.jookmax.v7.liquidity.model.LiquidityZone



class EqualHighDetector {


    fun detect(

        candles: List<MarketCandle>,

        tolerance: Double = 0.002

    ): LiquidityZone? {


        if (candles.size < 3)

            return null





        val latestHigh =

            candles.last().high





        val previousHigh =

            candles

                .dropLast(1)

                .maxOfOrNull {

                    it.high

                }

                ?: return null





        val difference =

            kotlin.math.abs(
                latestHigh - previousHigh
            ) / previousHigh





        if (difference <= tolerance) {


            return LiquidityZone(


                type = LiquidityType.EQUAL_HIGH,


                highPrice = maxOf(

                    latestHigh,

                    previousHigh

                ),


                lowPrice = minOf(

                    latestHigh,

                    previousHigh

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