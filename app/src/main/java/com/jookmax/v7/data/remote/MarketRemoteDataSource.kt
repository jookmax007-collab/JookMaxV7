package com.jookmax.v7.data.remote

import com.jookmax.v7.core.model.MarketHistory
import com.jookmax.v7.core.model.MarketPrice
import javax.inject.Inject


class MarketRemoteDataSource @Inject constructor(
    private val apiService: MarketApiService
) {


    suspend fun fetchMarketPrice(): MarketPrice? {

        return apiService.getLatestMarketPrice()

    }



    suspend fun fetchMarketHistory(): MarketHistory? {

        // بعداً API تاریخچه اضافه می‌شود

        return null

    }



    fun isConnected(): Boolean {

        // بعداً با Network state واقعی جایگزین می‌شود

        return true

    }



    fun disconnect() {

        // بعداً برای WebSocket استفاده می‌شود

    }


}