package com.jookmax.v7.liquidity.detector


import com.jookmax.v7.core.model.MarketCandle
import com.jookmax.v7.liquidity.model.LiquidityEvent



class FakeBreakoutDetector {



    fun detect(

        candles: List<MarketCandle>,

        breakoutLevel: Double,

        tolerance: Double = 0.003

    ): LiquidityEvent {



        if (candles.size < 2)

            return LiquidityEvent.NONE





        val latest =

            candles.last()





        val breakoutUp =

            latest.high > breakoutLevel





        val breakoutDown =

            latest.low < breakoutLevel





        val closeBackInside =

            latest.close <= breakoutLevel





        val closeBackAbove =

            latest.close >= breakoutLevel





        val fakeBullishBreakout =

            breakoutUp &&

            closeBackInside &&

            kotlin.math.abs(

                latest.high - breakoutLevel

            ) / breakoutLevel <= tolerance





        val fakeBearishBreakout =

            breakoutDown &&

            closeBackAbove &&

            kotlin.math.abs(

                breakoutLevel - latest.low

            ) / breakoutLevel <= tolerance





        return when {


            fakeBullishBreakout ->

                LiquidityEvent.FAKE_BREAKOUT





            fakeBearishBreakout ->

                LiquidityEvent.FAKE_BREAKOUT





            else ->

                LiquidityEvent.NONE

        }


    }


}