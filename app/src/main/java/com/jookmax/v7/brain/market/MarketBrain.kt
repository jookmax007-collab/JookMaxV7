package com.jookmax.v7.brain.market


import com.jookmax.v7.analysis.engine.TechnicalAnalyzer
import com.jookmax.v7.analysis.model.MarketAnalysis
import com.jookmax.v7.core.model.MarketCandle
import com.jookmax.v7.core.model.MarketPrice
import com.jookmax.v7.structure.MarketStructureEngine
import com.jookmax.v7.structure.model.MarketStructure

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
 *       +----------------+
 *       |                |
 *       v                v
 * TechnicalAnalyzer   MarketStructureEngine
 *       |                |
 *       v                v
 * MarketAnalysis   MarketStructure
 *
 *       |
 *       v
 *
 * MarketContext
 *
 */
@Singleton
class MarketBrain @Inject constructor(

    private val technicalAnalyzer: TechnicalAnalyzer,

    private val marketStructureEngine: MarketStructureEngine

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



        if (candles.size > 500) {

            candles.removeAt(0)

        }


    }





    fun getLatestMarketPrice(): MarketPrice? {

        return lastMarketPrice

    }





    
    fun getCandles(): List<MarketCandle> {


        return candles.toList()


    }

fun analyze(): MarketAnalysis {


        return technicalAnalyzer.analyze(

            candles.toList()

        )


    }





    fun analyzeStructure(): MarketStructure {


        return marketStructureEngine.analyze(

            candles.toList()

        )


    }fun reset() {


        lastMarketPrice = null


        candles.clear()


    }



}


