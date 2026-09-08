package com.jookmax.v7.brain.market


import com.jookmax.v7.analysis.engine.TechnicalAnalyzer
import com.jookmax.v7.analysis.model.MarketAnalysis
import com.jookmax.v7.core.model.MarketCandle
import com.jookmax.v7.core.model.MarketPrice

import javax.inject.Inject
import javax.inject.Singleton



/**
 * Market Intelligence Brain
 *
 * Flow:
 *
 * MarketCandle
 *       |
 *       v
 * MarketBrain
 *       |
 *       v
 * TechnicalAnalyzer
 *       |
 *       v
 * MarketAnalysis
 *
 */
@Singleton
class MarketBrain @Inject constructor(

    private val technicalAnalyzer: TechnicalAnalyzer

) {



    private var lastMarketPrice: MarketPrice? = null



    private val candles =
        mutableListOf<MarketCandle>()





    fun updateMarket(

        price: MarketPrice

    ) {

        lastMarketPrice = price

    }







    /**
     * Receive closed candle from market pipeline
     */
    fun updateCandle(

        candle: MarketCandle

    ) {


        candles.add(candle)



        /*
         * Keep analysis window stable
         *
         * 500 candles is enough for:
         * RSI
         * MA
         * MACD
         * ATR
         */
        if (candles.size > 500) {

            candles.removeAt(0)

        }


    }








    fun getLatestMarketPrice(): MarketPrice? {

        return lastMarketPrice

    }









    fun analyze(): MarketAnalysis {


        return technicalAnalyzer.analyze(

            candles.toList()

        )


    }








    fun reset() {


        lastMarketPrice = null


        candles.clear()


    }



}