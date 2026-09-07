package com.jookmax.v7.data.local


import com.jookmax.v7.core.model.MarketHistory
import com.jookmax.v7.core.model.MarketPrice
import com.jookmax.v7.data.local.dao.MarketDao
import com.jookmax.v7.data.mapper.MarketEntityMapper

import javax.inject.Inject



class MarketLocalDataSource @Inject constructor(


    private val marketDao: MarketDao,


    private val mapper: MarketEntityMapper


) {



    suspend fun saveMarketPrice(
        price: MarketPrice
    ) {


        val entity =
            mapper.mapToEntity(price)


        marketDao.insertMarketPrice(
            entity
        )

    }





    suspend fun getMarketPrice(): MarketPrice? {


        return marketDao
            .getMarketPrice()
            ?.let {

                mapper.mapToDomain(it)

            }


    }





    suspend fun saveMarketHistory(
        history: MarketHistory
    ) {


        // بعد از اضافه شدن Entity مربوط به History کامل می‌شود


    }





    suspend fun getMarketHistory(): MarketHistory? {


        return null


    }





    suspend fun clear() {


        marketDao.clearPrice()

        marketDao.clearCandles()


    }


}