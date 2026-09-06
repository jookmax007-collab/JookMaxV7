package com.jookmax.v7.data.remote


import com.jookmax.v7.core.model.MarketHistory
import com.jookmax.v7.core.model.MarketPrice

import javax.inject.Inject



class MarketRemoteDataSource @Inject constructor(

    private val apiService: MarketApiService

) {



    suspend fun fetchMarketPrice(): MarketPrice? {


        return try {


            apiService.getLatestMarketPrice()


        } catch (exception: Exception) {


            null


        }


    }




    suspend fun fetchMarketHistory(): MarketHistory? {


        return try {


            // Future:
            // apiService.getMarketHistory()


            null


        } catch (exception: Exception) {


            null


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
        // Close WebSocket
        // Release resources


    }


}