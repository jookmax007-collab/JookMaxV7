package com.jookmax.v7.data.local

import com.jookmax.v7.core.model.MarketPrice


class MarketLocalDataSource {


    private var cachedPrice: MarketPrice? = null



    fun saveMarketPrice(
        price: MarketPrice
    ) {

        cachedPrice = price

    }



    fun getMarketPrice(): MarketPrice? {

        return cachedPrice

    }



    fun clear() {

        cachedPrice = null

    }

}