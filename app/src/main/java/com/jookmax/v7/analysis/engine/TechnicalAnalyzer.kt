package com.jookmax.v7.analysis.engine


import com.jookmax.v7.analysis.indicator.ATR
import com.jookmax.v7.analysis.indicator.MACD
import com.jookmax.v7.analysis.indicator.MovingAverage
import com.jookmax.v7.analysis.indicator.RSI
import com.jookmax.v7.analysis.model.MarketAnalysis
import com.jookmax.v7.core.model.MarketCandle
import com.jookmax.v7.core.model.Symbol

import javax.inject.Inject
import javax.inject.Singleton



/**
 * Technical Analysis Engine
 *
 * Pipeline:
 *
 * MarketCandle
 *       |
 *       v
 * Price Series
 *       |
 *       +--> RSI
 *       |
 *       +--> Moving Average
 *       |
 *       +--> MACD
 *       |
 *       +--> ATR
 *       |
 *       v
 * MarketAnalysis
 *
 */
@Singleton
class TechnicalAnalyzer @Inject constructor(

    private val rsi: RSI,

    private val movingAverage: MovingAverage,

    private val macd: MACD,

    private val atr: ATR

) {



    fun analyze(

        candles: List<MarketCandle>

    ): MarketAnalysis {



        if (candles.isEmpty()) {


            return MarketAnalysis(

                symbol = Symbol(
                    code = "XAUUSD"
                ),

                trend = "UNKNOWN",

                rsi = 0.0,

                movingAverage = 0.0,

                volatility = 0.0,

                timestamp = System.currentTimeMillis()

            )


        }





        val latestCandle =
            candles.last()



        val closePrices =
            candles.map {

                it.close

            }





        val rsiValue =

            rsi.calculate(
                closePrices
            )





        val movingAverageValue =

            movingAverage.calculate(
                closePrices
            )





        val volatilityValue =

            atr.calculate(
                closePrices
            )





        // MACD foundation calculation
        // Reserved for future signal generation

        macd.calculate(
            closePrices
        )





        val trend =

            when {


                latestCandle.close > movingAverageValue ->

                    "BULLISH"



                latestCandle.close < movingAverageValue ->

                    "BEARISH"



                else ->

                    "SIDEWAYS"


            }





        return MarketAnalysis(


            symbol = latestCandle.symbol,


            trend = trend,


            rsi = rsiValue,


            movingAverage = movingAverageValue,


            volatility = volatilityValue,


            timestamp = System.currentTimeMillis()


        )


    }



}
