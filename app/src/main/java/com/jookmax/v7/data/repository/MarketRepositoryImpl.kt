package com.jookmax.v7.data.repository


import com.jookmax.v7.core.model.MarketHistory
import com.jookmax.v7.core.model.MarketPrice
import com.jookmax.v7.data.local.MarketLocalDataSource
import com.jookmax.v7.domain.repository.MarketDataSource
import com.jookmax.v7.domain.repository.MarketRepository

import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class MarketRepositoryImpl @Inject constructor(


    private val localDataSource: MarketLocalDataSource,


    private val remoteDataSource: MarketDataSource


) : MarketRepository {





    override suspend fun getLatestMarketPrice(): MarketPrice? {


        val cached =
            localDataSource.getMarketPrice()



        return cached


    }





    override suspend fun getCachedMarketPrice(): MarketPrice? {


        return localDataSource.getMarketPrice()


    }





    override suspend fun getMarketHistory(): MarketHistory? {


        val candles =

            remoteDataSource.getCandles()



        if (candles.isNotEmpty()) {


            val history = MarketHistory(


                symbol = candles.first().symbol,


                candles = candles,


                timestamp = System.currentTimeMillis()


            )



            localDataSource.saveMarketHistory(

                history

            )



            return history


        }



        return localDataSource.getMarketHistory()


    }





    override suspend fun clearCache() {


        localDataSource.clear()


    }


}