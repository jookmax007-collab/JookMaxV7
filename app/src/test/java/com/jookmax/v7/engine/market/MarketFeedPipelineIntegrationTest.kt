package com.jookmax.v7.engine.market


import com.jookmax.v7.core.event.EngineEvent
import com.jookmax.v7.core.event.EventBus
import com.jookmax.v7.core.event.MarketEvent
import com.jookmax.v7.core.model.*
import com.jookmax.v7.domain.repository.CandleRepository
import com.jookmax.v7.domain.repository.MarketRepository
import com.jookmax.v7.engine.market.tick.TickEngine
import com.jookmax.v7.engine.market.tick.TickProcessor
import com.jookmax.v7.engine.market.tick.TickValidator

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

import org.junit.Assert.assertTrue
import org.junit.Test



class MarketFeedPipelineIntegrationTest {



    @Test
    fun `market feed should convert tick into candle pipeline`() =
        runBlocking {



            val candleRepository =
                FakeCandleRepository()



            val eventBus =
                EventBus()



            val marketRepository =
                FakeMarketRepository()



            val tickProcessor =
                TickProcessor(

                    tickValidator = TickValidator(),

                    tickEngine = TickEngine()

                )



            val manager =
                MarketFeedManager(

                    marketRepository = marketRepository,

                    candleRepository = candleRepository,

                    eventBus = eventBus,

                    tickProcessor = tickProcessor

                )



            val events =
                mutableListOf<EngineEvent>()



            val job =
                launch {


                    eventBus.events.collect { event ->

                        events.add(event)

                    }

                }



            manager.start(this@runBlocking)



            delay(200)



            job.cancel()



            assertTrue(

                events.any {

                    it is MarketEvent.CandleClosed

                } || candleRepository.items.isNotEmpty()

            )


        }



}





class FakeMarketRepository : MarketRepository {



    override fun observeLiveTicks(): Flow<MarketTick> {


        val now =
            System.currentTimeMillis()



        return flowOf(


            MarketTick(

                symbol =
                Symbol(

                    code = "XAUUSD",

                    description = "Gold / USD"

                ),

                price = 2500.0,

                timestamp = now

            ),


            MarketTick(

                symbol =
                Symbol(

                    code = "XAUUSD",

                    description = "Gold / USD"

                ),

                price = 2510.0,

                timestamp = now + 4000000L

            )


        )


    }





    override fun observeLivePrice(): Flow<MarketPrice> {

        return flowOf()

    }



    override suspend fun getLatestMarketPrice(): MarketPrice? {

        return null

    }



    override suspend fun getCachedMarketPrice(): MarketPrice? {

        return null

    }



    override suspend fun getMarketHistory(): MarketHistory? {

        return null

    }



    override fun connectLiveFeed() {

    }


    override fun disconnectLiveFeed() {

    }


    override suspend fun clearCache() {

    }


}






class FakeCandleRepository : CandleRepository {



    val items =
        mutableListOf<MarketCandle>()



    override suspend fun saveCandles(

        candles: List<MarketCandle>

    ) {

        items.addAll(candles)

    }





    override suspend fun getCandles(): List<MarketCandle> {

        return items

    }





    override suspend fun clear() {

        items.clear()

    }


}


