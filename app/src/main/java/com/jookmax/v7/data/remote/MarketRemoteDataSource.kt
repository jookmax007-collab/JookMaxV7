package com.jookmax.v7.data.remote


import com.jookmax.v7.core.model.MarketCandle
import com.jookmax.v7.core.model.MarketHistory
import com.jookmax.v7.core.model.MarketPrice
import com.jookmax.v7.core.model.MarketQuote
import com.jookmax.v7.core.model.MarketTick

import com.jookmax.v7.data.mapper.CandleMapper
import com.jookmax.v7.data.mapper.MarketQuoteMapper
import com.jookmax.v7.data.mapper.MarketRemoteMapper
import com.jookmax.v7.data.mapper.TickMapper

import com.jookmax.v7.data.remote.socket.MarketSocketClient
import com.jookmax.v7.data.remote.socket.MarketSocketListener

import com.jookmax.v7.domain.repository.MarketDataSource

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

import javax.inject.Inject



class MarketRemoteDataSource @Inject constructor(


    private val apiService: MarketApiService,


    private val marketRemoteMapper: MarketRemoteMapper,


    private val marketQuoteMapper: MarketQuoteMapper,


    private val tickMapper: TickMapper,


    private val candleMapper: CandleMapper,


    private val marketSocketClient: MarketSocketClient


) : MarketDataSource {



    private val _livePrices =
        MutableSharedFlow<MarketPrice>(
            replay = 1,
            extraBufferCapacity = 64
        )



    private val livePrices: Flow<MarketPrice> =
        _livePrices.asSharedFlow()





    /**
     * Raw tick stream.
     *
     * Used by TickEngine
     * for candle generation.
     */
    private val _liveTicks =
        MutableSharedFlow<MarketTick>(
            replay = 1,
            extraBufferCapacity = 64
        )



    private val liveTicks: Flow<MarketTick> =
        _liveTicks.asSharedFlow()





    init {


        marketSocketClient.setListener(

            object : MarketSocketListener {


                override fun onConnected() {

                    // Authentication handled by client.

                }





                override fun onTick(

                    tick: MarketTick

                ) {



                    /*
                     * Raw tick pipeline
                     *
                     * MarketSocket
                     *       |
                     *       v
                     * TickEngine
                     */

                    _liveTicks.tryEmit(
                        tick
                    )





                    /*
                     * Price update pipeline
                     *
                     * MarketSocket
                     *       |
                     *       v
                     * MarketBrain
                     */

                    _livePrices.tryEmit(


                        MarketPrice(

                            symbol = tick.symbol,

                            price = tick.price,

                            timestamp = tick.timestamp,

                            bid = tick.bid,

                            ask = tick.ask

                        )


                    )


                }





                override fun onDisconnected(

                    code: Int,

                    reason: String?

                ) {


                    // Connection state handled by client.


                }






                override fun onFailure(

                    throwable: Throwable

                ) {


                    // Failure state handled by client.


                }



            }

        )


    }







    override fun observeLivePrice():

            Flow<MarketPrice> {


        return livePrices


    }






    override fun observeLiveTicks():

            Flow<MarketTick> {


        return liveTicks


    }









    suspend fun fetchMarketPrice():

            MarketPrice? {


        return try {


            apiService

                .getLatestMarketPrice()

                ?.let {


                    marketRemoteMapper.mapToDomain(it)


                }


        } catch (_: Exception) {


            null


        }


    }






    override suspend fun getLatestPrice():

            MarketPrice? {


        return fetchMarketPrice()


    }







    suspend fun fetchMarketQuote():

            MarketQuote? {


        return try {


            apiService

                .getMarketQuote()

                ?.let {


                    marketQuoteMapper.mapToDomain(it)


                }


        } catch (_: Exception) {


            null


        }


    }






    override suspend fun getLatestQuote():

            MarketQuote? {


        return fetchMarketQuote()


    }








    suspend fun fetchLatestTick():

            MarketTick? {


        return try {


            apiService

                .getLatestTick()

                ?.let {


                    tickMapper.mapToDomain(it)


                }


        } catch (_: Exception) {


            null


        }


    }






    override suspend fun getLatestTick():

            MarketTick? {


        return fetchLatestTick()


    }









    override suspend fun getCandles():

            List<MarketCandle> {


        return try {


            apiService

                .getCandles()

                .map {


                    candleMapper.mapToDomain(it)


                }


        } catch (_: Exception) {


            emptyList()


        }


    }









    suspend fun fetchMarketHistory():

            MarketHistory? {


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









    fun connectLiveFeed() {


        marketSocketClient.connect()


    }






    fun disconnectLiveFeed() {


        marketSocketClient.disconnect()


    }






    fun isConnected():

            Boolean {


        return marketSocketClient.connectionState.value

            .let { state ->


                state is com.jookmax.v7.data.remote.socket.SocketConnectionState.Connected


            }


    }







    fun disconnect() {


        disconnectLiveFeed()


    }



}