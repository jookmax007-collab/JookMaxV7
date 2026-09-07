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







    override suspend fun clearCache() {


        localDataSource.clear()


    }



}