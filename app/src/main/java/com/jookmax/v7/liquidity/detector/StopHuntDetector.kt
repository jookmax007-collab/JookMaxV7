package com.jookmax.v7.liquidity.detector


import com.jookmax.v7.core.model.MarketCandle
import com.jookmax.v7.liquidity.model.LiquidityEvent



class StopHuntDetector {



    fun detect(

        candles: List<MarketCandle>,

        liquidityPrice: Double,

        tolerance: Double = 0.003

    ): LiquidityEvent {



        if (candles.size < 2)

            return LiquidityEvent.NONE





        val latest =

            candles.last()





        val sweepUp =

            latest.high > liquidityPrice &&

            latest.close < liquidityPrice





        val sweepDown =

            latest.low < liquidityPrice &&

            latest.close > liquidityPrice





        val upperStopHunt =

            sweepUp &&

            kotlin.math.abs(

                latest.high - liquidityPrice

            ) / liquidityPrice <= tolerance





        val lowerStopHunt =

            sweepDown &&

            kotlin.math.abs(

                liquidityPrice - latest.low

            ) / liquidityPrice <= tolerance





        return when {


            upperStopHunt ->

                LiquidityEvent.STOP_HUNT





            lowerStopHunt ->

                LiquidityEvent.STOP_HUNT





            else ->

                LiquidityEvent.NONE

        }


    }


}