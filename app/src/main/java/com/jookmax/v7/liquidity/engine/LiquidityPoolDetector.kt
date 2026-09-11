package com.jookmax.v7.liquidity.engine


import com.jookmax.v7.core.model.MarketCandle
import com.jookmax.v7.liquidity.detector.EqualHighDetector
import com.jookmax.v7.liquidity.detector.EqualLowDetector
import com.jookmax.v7.liquidity.model.LiquidityType
import com.jookmax.v7.liquidity.model.LiquidityZone



class LiquidityPoolDetector(


    private val equalHighDetector: EqualHighDetector = EqualHighDetector(),


    private val equalLowDetector: EqualLowDetector = EqualLowDetector()


) {



    fun detect(

        candles: List<MarketCandle>

    ): LiquidityZone? {



        if (candles.isEmpty())

            return null





        val equalHigh =

            equalHighDetector.detect(

                candles

            )





        val equalLow =

            equalLowDetector.detect(

                candles

            )





        if (equalHigh != null) {



            return LiquidityZone(


                type = LiquidityType.LIQUIDITY_POOL,


                highPrice = equalHigh.highPrice,


                lowPrice = equalHigh.lowPrice,


                strength = 0.8,


                touched = false,


                swept = false,


                timestamp = candles.last().timestamp


            )

        }





        if (equalLow != null) {



            return LiquidityZone(


                type = LiquidityType.LIQUIDITY_POOL,


                highPrice = equalLow.highPrice,


                lowPrice = equalLow.lowPrice,


                strength = 0.8,


                touched = false,


                swept = false,


                timestamp = candles.last().timestamp


            )

        }





        return null

    }


}