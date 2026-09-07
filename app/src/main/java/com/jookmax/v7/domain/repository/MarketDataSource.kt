package com.jookmax.v7.domain.repository

import com.jookmax.v7.core.model.Candle
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


    suspend fun getLatestQuote(): MarketQuote?


    suspend fun getLatestTick(): Tick?


    suspend fun getCandles(): List<Candle>


}