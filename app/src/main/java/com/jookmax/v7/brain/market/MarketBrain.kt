package com.jookmax.v7.brain.market

import com.jookmax.v7.core.model.MarketPrice


class MarketBrain {


    private var lastMarketPrice: MarketPrice? = null



    fun updateMarket(price: MarketPrice) {

        lastMarketPrice = price

    }



    fun getLatestMarketPrice(): MarketPrice? {

        return lastMarketPrice

    }



    fun analyze(): MarketAnalysis {

        val price = lastMarketPrice


        return if (price == null) {

            MarketAnalysis(
                status = "NO_DATA",
                confidence = 0.0
            )

        } else {

            MarketAnalysis(
                status = "READY",
                confidence = 0.5
            )

        }
    }



    fun reset(){

        lastMarketPrice = null

    }
}



data class MarketAnalysis(

    val status: String,

    val confidence: Double

)