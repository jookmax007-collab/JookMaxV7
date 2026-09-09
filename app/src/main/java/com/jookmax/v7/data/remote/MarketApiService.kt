package com.jookmax.v7.data.remote


import com.jookmax.v7.data.remote.dto.CandleDto
import com.jookmax.v7.data.remote.dto.MarketQuoteDto
import com.jookmax.v7.data.remote.dto.TickDto
import com.jookmax.v7.data.remote.dto.MarketPriceDto

import retrofit2.http.GET



interface MarketApiService {


    @GET("market/latest")
    suspend fun getLatestMarketPrice(): MarketPriceDto?



    @GET("market/quote")
    suspend fun getMarketQuote(): MarketQuoteDto?



    @GET("market/tick")
    suspend fun getLatestTick(): TickDto?



    @GET("market/candles")
    suspend fun getCandles(): List<CandleDto>



}
