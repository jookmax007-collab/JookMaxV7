package com.jookmax.v7.data.local


import com.jookmax.v7.core.model.MarketCandle
import com.jookmax.v7.core.model.MarketHistory
import com.jookmax.v7.core.model.MarketPrice
import com.jookmax.v7.core.model.MarketQuote
import com.jookmax.v7.core.model.MarketTick
import com.jookmax.v7.core.model.Symbol

import com.jookmax.v7.data.local.dao.MarketDao
import com.jookmax.v7.data.mapper.MarketEntityMapper

import com.jookmax.v7.domain.repository.MarketDataSource

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map

import javax.inject.Inject



class MarketLocalDataSource @Inject constructor(

    private val marketDao: MarketDao,

    private val mapper: MarketEntityMapper

) : MarketDataSource {



    suspend fun saveMarketPrice(
        price: MarketPrice
    ) {

        val entity =
            mapper.mapToEntity(price)

        marketDao.insertMarketPrice(entity)

    }





    suspend fun getMarketPrice(): MarketPrice? {

        return marketDao
            .getMarketPrice()
            ?.let {

                mapper.mapToDomain(it)

            }

    }





    override suspend fun getLatestPrice(): MarketPrice? {

        return getMarketPrice()

    }





    override fun observeLivePrice(): Flow<MarketPrice> {

        return marketDao
            .observeMarketPrice()
            .map { entity ->


                entity?.let {

                    mapper.mapToDomain(it)

                } ?: MarketPrice(

                    symbol = Symbol(
                        code = "XAUUSD"
                    ),

                    price = 0.0,

                    timestamp = System.currentTimeMillis()

                )


            }

    }





    override fun observeLiveTicks(): Flow<MarketTick> {

        /*
            Local storage does not produce
            real-time ticks.

            Tick stream comes from:
            
            MarketSocketClient
                    |
                    v
            MarketRemoteDataSource
                    |
                    v
            TickEngine
        */

        return emptyFlow()

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





    override suspend fun getLatestQuote(): MarketQuote? {


        val price =
            getMarketPrice()
                ?: return null



        return MarketQuote(

            symbol = price.symbol,

            bid = price.bid ?: price.price,

            ask = price.ask ?: price.price,

            last = price.price,

            timestamp = price.timestamp

        )


    }





    override suspend fun getLatestTick(): MarketTick? {


        val price =
            getMarketPrice()
                ?: return null



        return MarketTick(

            symbol = price.symbol,

            price = price.price,

            timestamp = price.timestamp,

            bid = price.bid,

            ask = price.ask

        )


    }





    override suspend fun getCandles(): List<MarketCandle> {


        return marketDao
            .getCandles()
            .map {


                mapper.mapCandleToDomain(it)


            }


    }





    suspend fun clear() {


        marketDao.clearPrice()

        marketDao.clearCandles()


    }



}