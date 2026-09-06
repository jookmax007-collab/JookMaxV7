package com.jookmax.v7.data.repository


import com.jookmax.v7.core.model.MarketPrice
import com.jookmax.v7.data.local.MarketLocalDataSource
import com.jookmax.v7.data.mapper.MarketMapper
import com.jookmax.v7.data.remote.MarketRemoteDataSource
import com.jookmax.v7.domain.repository.MarketRepository


class MarketRepositoryImpl(

    private val remoteDataSource: MarketRemoteDataSource,

    private val localDataSource: MarketLocalDataSource,

    private val mapper: MarketMapper

) : MarketRepository {



    override suspend fun getLatestMarketPrice(): MarketPrice? {


        val remotePrice =
            remoteDataSource.fetchMarketPrice()


        return if (remotePrice != null) {


            localDataSource.saveMarketPrice(
                remotePrice
            )


            remotePrice


        } else {


            localDataSource.getMarketPrice()


        }

    }




    override fun getCachedMarketPrice(): MarketPrice? {


        return localDataSource.getMarketPrice()

    }





    override fun clearCache() {


        localDataSource.clear()

    }


}