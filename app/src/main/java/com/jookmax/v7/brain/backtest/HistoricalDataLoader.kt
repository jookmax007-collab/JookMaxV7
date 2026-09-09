package com.jookmax.v7.brain.backtest


import com.jookmax.v7.core.model.MarketCandle
import com.jookmax.v7.domain.repository.MarketRepository

import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class HistoricalDataLoader @Inject constructor(

    private val marketRepository: MarketRepository

) {



    suspend fun load(): List<MarketCandle> {


        val history =

            marketRepository.getMarketHistory()



        return history?.candles
            ?: emptyList()


    }


}
