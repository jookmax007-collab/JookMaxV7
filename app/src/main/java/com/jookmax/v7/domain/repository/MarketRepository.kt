package com.jookmax.v7.domain.repository


import com.jookmax.v7.core.model.MarketHistory
import com.jookmax.v7.core.model.MarketPrice
import com.jookmax.v7.core.model.MarketTick

import kotlinx.coroutines.flow.Flow


interface MarketRepository {


    suspend fun getLatestMarketPrice(): MarketPrice?


    suspend fun getCachedMarketPrice(): MarketPrice?


    suspend fun getMarketHistory(): MarketHistory?



    fun observeLivePrice(): Flow<MarketPrice>



    /**
     * Raw Tick stream.
     */
    fun observeLiveTicks(): Flow<MarketTick>



    suspend fun clearCache()


}
