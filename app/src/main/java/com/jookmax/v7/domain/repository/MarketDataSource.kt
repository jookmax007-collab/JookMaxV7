package com.jookmax.v7.domain.repository


import com.jookmax.v7.core.model.MarketCandle
import com.jookmax.v7.core.model.MarketPrice
import com.jookmax.v7.core.model.MarketQuote
import com.jookmax.v7.core.model.Tick



/**
 * Contract for market data providers.
 *
 * Implementations can be:
 * - Remote API
 * - WebSocket
 * - Local cache
 */
interface MarketDataSource {


    suspend fun getLatestPrice(): MarketPrice?


    suspend fun getLatestQuote(): MarketQuote?


    suspend fun getLatestTick(): Tick?


    suspend fun getCandles(): List<MarketCandle>


}