package com.jookmax.v7.data.local


import com.jookmax.v7.core.model.MarketHistory
import com.jookmax.v7.core.model.MarketPrice


class MarketLocalDataSource {


    private var cachedPrice: MarketPrice? = null


    private var cachedHistory: MarketHistory? = null



    fun saveMarketPrice(
        price: MarketPrice
    ) {

        cachedPrice = price

    }



    fun getMarketPrice(): MarketPrice? {

        return cachedPrice

    }



    fun saveMarketHistory(
        history: MarketHistory
    ) {

        cachedHistory = history

    }



    fun getMarketHistory(): MarketHistory? {

        return cachedHistory

    }



    fun clear() {

        cachedPrice = null

        cachedHistory = null

    }


}