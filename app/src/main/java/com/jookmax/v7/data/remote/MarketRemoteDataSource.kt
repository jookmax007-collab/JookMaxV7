package com.jookmax.v7.data.remote


import com.jookmax.v7.core.model.Candle
import com.jookmax.v7.core.model.MarketQuote
import com.jookmax.v7.core.model.Tick
import com.jookmax.v7.domain.repository.MarketDataSource

import javax.inject.Inject



/**
 * Remote market data provider.
 *
 * Future implementations:
 * - REST API
 * - WebSocket
 * - Streaming market feed
 */
class MarketRemoteDataSource @Inject constructor(

    private val apiService: MarketApiService

) : MarketDataSource {



    override suspend fun getLatestQuote(): MarketQuote? {


        return try {


            apiService.getLatestQuote()


        } catch (exception: Exception) {


            null

        }


    }





    override suspend fun getLatestTick(): Tick? {


        return try {


            apiService.getLatestTick()


        } catch (exception: Exception) {


            null

        }


    }





    override suspend fun getCandles(): List<Candle> {


        return try {


            apiService.getCandles()


        } catch (exception: Exception) {


            emptyList()

        }


    }





    fun isConnected(): Boolean {


        // Future:
        // NetworkMonitor
        // WebSocket state


        return true

    }





    fun disconnect() {


        // Future:
        // Close websocket
        // Release resources


    }


}