package com.jookmax.v7.brain.market


import com.jookmax.v7.analysis.engine.TechnicalAnalyzer
import com.jookmax.v7.analysis.model.MarketAnalysis
import com.jookmax.v7.core.model.MarketCandle
import com.jookmax.v7.core.model.MarketPrice

import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class MarketBrain @Inject constructor(

    private val technicalAnalyzer: TechnicalAnalyzer

) {



    private var lastMarketPrice: MarketPrice? = null


    private var candles: List<MarketCandle> = emptyList()





    fun updateMarket(
        price: MarketPrice
    ) {

        lastMarketPrice = price

    }






    fun updateCandles(
        marketCandles: List<MarketCandle>
    ) {

        candles = marketCandles

    }






    fun getLatestMarketPrice(): MarketPrice? {

        return lastMarketPrice

    }






    fun analyze(): MarketAnalysis {


        return technicalAnalyzer.analyze(

            candles

        )


    }






    fun reset(){


        lastMarketPrice = null

        candles = emptyList()


    }



}