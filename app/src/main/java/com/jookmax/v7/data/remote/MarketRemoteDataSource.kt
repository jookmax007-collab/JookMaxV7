package com.jookmax.v7.data.remote


import com.jookmax.v7.core.model.Candle
import com.jookmax.v7.core.model.MarketHistory
import com.jookmax.v7.core.model.MarketPrice
import com.jookmax.v7.core.model.MarketQuote
import com.jookmax.v7.core.model.Tick

import com.jookmax.v7.data.mapper.CandleMapper
import com.jookmax.v7.data.mapper.MarketQuoteMapper
import com.jookmax.v7.data.mapper.MarketRemoteMapper
import com.jookmax.v7.data.mapper.TickMapper

import com.jookmax.v7.domain.repository.MarketDataSource

import javax.inject.Inject


class MarketRemoteDataSource @Inject constructor(

    private val apiService: MarketApiService,

    private val marketRemoteMapper: MarketRemoteMapper,

    private val marketQuoteMapper: MarketQuoteMapper,

    private val tickMapper: TickMapper,

    private val candleMapper: CandleMapper

) : MarketDataSource {



    suspend fun fetchMarketPrice(): MarketPrice? {

        return try {

            apiService
                .getLatestMarketPrice()
                ?.let {

                    marketRemoteMapper.mapToDomain(it)

                }

        } catch (exception: Exception) {

            null

        }

    }



    override suspend fun getLatestPrice(): MarketPrice? {

        return fetchMarketPrice()

    }



    suspend fun fetchMarketQuote(): MarketQuote? {

        return try {

            apiService
                .getMarketQuote()
                ?.let {

                    marketQuoteMapper.mapToDomain(it)

                }

        } catch (exception: Exception) {

            null

        }

    }



    override suspend fun getLatestQuote(): MarketQuote? {

        return fetchMarketQuote()

    }



    suspend fun fetchLatestTick(): Tick? {

        return try {

            apiService
                .getLatestTick()
                ?.let {

                    tickMapper.mapToDomain(it)

                }

        } catch (exception: Exception) {

            null

        }

    }



    override suspend fun getLatestTick(): Tick? {

        return fetchLatestTick()

    }



    override suspend fun getCandles(): List<Candle> {

        return try {

            apiService
                .getCandles()
                .map {

                    candleMapper.mapToDomain(it)

                }

        } catch (exception: Exception) {

            emptyList()

        }

    }



    suspend fun fetchMarketHistory(): MarketHistory? {

        val candles = getCandles()

        if (candles.isEmpty()) {

            return null

        }


        return MarketHistory(

            symbol = candles.first().symbol,

            candles = candles,

            timestamp = System.currentTimeMillis()

        )

    }



    fun isConnected(): Boolean {

        return true

    }



    fun disconnect() {

        // Future WebSocket close

    }


}