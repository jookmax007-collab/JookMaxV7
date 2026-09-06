package com.jookmax.v7.data.remote

import com.jookmax.v7.core.model.MarketPrice


class MarketRemoteDataSource {


    suspend fun fetchMarketPrice(): MarketPrice? {

        // Future:
        // API Call
        // WebSocket Request
        // External Market Provider


        return null
    }



    suspend fun fetchLatestPrices(): List<MarketPrice> {

        // Future:
        // Receive market history data


        return emptyList()

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