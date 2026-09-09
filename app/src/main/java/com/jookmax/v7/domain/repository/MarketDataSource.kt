package com.jookmax.v7.domain.repository

import com.jookmax.v7.core.model.MarketCandle
import com.jookmax.v7.core.model.MarketPrice
import com.jookmax.v7.core.model.MarketQuote
import com.jookmax.v7.core.model.MarketTick
import kotlinx.coroutines.flow.Flow


/**
 * Contract for market data providers.
 *
 * Implementations can be:
 * - Remote REST API
 * - WebSocket
 * - Local cache
 */
interface MarketDataSource {


    suspend fun getLatestPrice(): MarketPrice?


    suspend fun getLatestQuote(): MarketQuote?


    suspend fun getLatestTick(): MarketTick?


    suspend fun getCandles(): List<MarketCandle>



    /**
     * Live market price stream.
     */
    fun observeLivePrice(): Flow<MarketPrice>



    /**
     * Raw market tick stream.
     *
     * Used by Tick Engine
     * for candle generation.
     */
    fun observeLiveTicks(): Flow<MarketTick>


}
