package com.jookmax.v7.domain.repository

import com.jookmax.v7.core.model.MarketCandle

interface CandleRepository {

    suspend fun getCandles(): List<MarketCandle>

    suspend fun saveCandles(
        candles: List<MarketCandle>
    )

    suspend fun clear()

}
