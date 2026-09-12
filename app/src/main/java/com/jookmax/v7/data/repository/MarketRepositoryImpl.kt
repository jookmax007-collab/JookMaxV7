package com.jookmax.v7.data.repository


import com.jookmax.v7.core.model.MarketHistory
import com.jookmax.v7.core.model.MarketPrice
import com.jookmax.v7.core.model.MarketTick

import com.jookmax.v7.data.local.MarketLocalDataSource

import com.jookmax.v7.domain.repository.MarketDataSource
import com.jookmax.v7.domain.repository.MarketRepository

import kotlinx.coroutines.flow.Flow

import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class MarketRepositoryImpl @Inject constructor(


    private val localDataSource: MarketLocalDataSource,


    private val remoteDataSource: MarketDataSource


) : MarketRepository {



    override suspend fun getLatestMarketPrice(): MarketPrice? {


        return try {


            val remotePrice =
                remoteDataSource.getLatestPrice()



            if (remotePrice != null) {


                localDataSource.saveMarketPrice(
                    remotePrice
                )


                remotePrice


            } else {


                localDataSource.getLatestPrice()


            }


        } catch (exception: Exception) {


            localDataSource.getLatestPrice()


        }


    }





    override suspend fun getCachedMarketPrice(): MarketPrice? {


        return localDataSource.getLatestPrice()


    }





    override suspend fun getMarketHistory(): MarketHistory? {


        return try {


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


                history


            } else {


                localDataSource.getMarketHistory()


            }


        } catch (exception: Exception) {


            localDataSource.getMarketHistory()


        }


    }





    override fun observeLivePrice(): Flow<MarketPrice> {


        return remoteDataSource.observeLivePrice()


    }





    /**
     * Raw market tick stream.
     *
     * Used by Tick Engine
     * for candle generation.
     */
    override fun observeLiveTicks(): Flow<MarketTick> {


        return remoteDataSource.observeLiveTicks()


    }





    override fun connectLiveFeed() {

        (remoteDataSource as? com.jookmax.v7.data.remote.MarketRemoteDataSource)
            ?.connectLiveFeed()

    }



    override fun disconnectLiveFeed() {

        (remoteDataSource as? com.jookmax.v7.data.remote.MarketRemoteDataSource)
            ?.disconnectLiveFeed()

    }



    override suspend fun clearCache() {


        localDataSource.clear()


    }


}

