package com.jookmax.v7.data.remote


import com.jookmax.v7.core.model.MarketPrice
import retrofit2.http.GET



interface MarketApiService {


    @GET("market/latest")
    suspend fun getLatestMarketPrice(): MarketPrice?



}