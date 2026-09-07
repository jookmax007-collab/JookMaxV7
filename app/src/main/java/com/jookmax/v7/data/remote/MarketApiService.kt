package com.jookmax.v7.data.remote


import com.jookmax.v7.core.model.Candle
import com.jookmax.v7.core.model.MarketPrice
import com.jookmax.v7.core.model.MarketQuote
import com.jookmax.v7.core.model.Tick

import retrofit2.http.GET



interface MarketApiService {



    @GET("market/latest")
    suspend fun getLatestMarketPrice(): MarketPrice?




    /**
     * Future market quote endpoint.
     */
    @GET("market/quote")
    suspend fun getLatestQuote(): MarketQuote?




    /**
     * Future tick stream endpoint.
     */
    @GET("market/tick")
    suspend fun getLatestTick(): Tick?




    /**
     * Future historical candles endpoint.
     */
    @GET("market/candles")
    suspend fun getCandles(): List<Candle>



}