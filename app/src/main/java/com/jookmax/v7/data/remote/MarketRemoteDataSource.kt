package com.jookmax.v7.data.remote


import com.jookmax.v7.core.model.MarketHistory
import com.jookmax.v7.core.model.MarketPrice


class MarketRemoteDataSource {


    suspend fun fetchMarketPrice(): MarketPrice? {

        // Future:
        // Retrofit API Call
        // WebSocket Request
        // External Market Provider


        return null

    }



    suspend fun fetchMarketHistory(): MarketHistory? {

        // Future:
        // Receive historical candles
        // API / WebSocket / Market Provider


        return null

    }



    fun isConnected(): Boolean {

        // Future:
        // Check API/WebSocket connection


        return false

    }



    fun disconnect() {

        // Future:
        // Close network connection

    }


}