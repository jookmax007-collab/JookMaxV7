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


        val entities =
            history.candles.map {

                mapper.mapCandleToEntity(it)

            }


        marketDao.insertCandles(
            entities
        )

    }





    suspend fun getMarketHistory(): MarketHistory? {


        val candles =

            marketDao
                .getCandles()
                .map {

                    mapper.mapCandleToDomain(it)

                }



        if (candles.isEmpty()) {

            return null

        }



        return MarketHistory(

            symbol = candles.first().symbol,

            candles = candles,

            timestamp = System.currentTimeMillis()

        )


    }





    suspend fun clear() {


        marketDao.clearPrice()

        marketDao.clearCandles()


    }


}