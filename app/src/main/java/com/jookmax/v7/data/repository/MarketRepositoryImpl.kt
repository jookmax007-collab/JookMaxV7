package com.jookmax.v7.data.repository


import com.jookmax.v7.core.model.MarketHistory
import com.jookmax.v7.core.model.MarketPrice

import com.jookmax.v7.data.local.MarketLocalDataSource
import com.jookmax.v7.data.remote.MarketRemoteDataSource

import com.jookmax.v7.domain.repository.MarketRepository

import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class MarketRepositoryImpl @Inject constructor(


    private val localDataSource: MarketLocalDataSource,


    private val remoteDataSource: MarketRemoteDataSource


) : MarketRepository {




    override suspend fun getLatestMarketPrice(): MarketPrice? {


        val remotePrice =

            remoteDataSource.fetchMarketPrice()



        if (remotePrice != null) {


            localDataSource.saveMarketPrice(

                remotePrice

            )


            return remotePrice

        }



        return localDataSource.getMarketPrice()


    }







    override suspend fun getCachedMarketPrice(): MarketPrice? {


        return localDataSource.getMarketPrice()


    }








    override suspend fun getMarketHistory(): MarketHistory? {


        val remoteHistory =

            remoteDataSource.fetchMarketHistory()



        if (remoteHistory != null) {


            localDataSource.saveMarketHistory(

                remoteHistory

            )


            return remoteHistory

        }



        return localDataSource.getMarketHistory()


    }








    override suspend fun clearCache() {


        localDataSource.clear()


    }



}