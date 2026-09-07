package com.jookmax.v7.domain.repository


import com.jookmax.v7.core.model.MarketHistory
import com.jookmax.v7.core.model.MarketPrice



interface MarketRepository {


    suspend fun getLatestMarketPrice(): MarketPrice?



    suspend fun getCachedMarketPrice(): MarketPrice?



    suspend fun getMarketHistory(): MarketHistory?



    suspend fun clearCache()



}