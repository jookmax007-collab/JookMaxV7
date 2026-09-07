package com.jookmax.v7.data.remote


import com.jookmax.v7.core.model.Candle
import com.jookmax.v7.core.model.MarketQuote
import com.jookmax.v7.core.model.Tick
import com.jookmax.v7.data.mapper.MarketRemoteMapper
import com.jookmax.v7.domain.repository.MarketDataSource

import javax.inject.Inject



class MarketRemoteDataSource @Inject constructor(


    private val apiService: MarketApiService,


    private val mapper: MarketRemoteMapper


) : MarketDataSource {



    override suspend fun getLatestQuote(): MarketQuote? {


        return null


    }





    override suspend fun getLatestTick(): Tick? {


        return null


    }





    override suspend fun getCandles(): List<Candle> {


        return emptyList()


    }





    suspend fun fetchMarketPrice() = try {


        apiService
            .getLatestMarketPrice()
            ?.let {


                mapper.mapToDomain(it)


            }



    } catch (e: Exception) {


        null


    }





    suspend fun fetchMarketHistory() = null





    fun isConnected(): Boolean {


        return true


    }





    fun disconnect() {


    }



}