package com.jookmax.v7.engine.market


import com.jookmax.v7.core.event.EventBus
import com.jookmax.v7.core.event.MarketEvent

import com.jookmax.v7.domain.repository.CandleRepository
import com.jookmax.v7.domain.repository.MarketRepository

import com.jookmax.v7.engine.market.tick.TickProcessor

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

import javax.inject.Inject
import javax.inject.Singleton



/**
 * Market Feed Pipeline
 *
 * Flow:
 *
 * MarketSocket
 *      |
 *      v
 * MarketRemoteDataSource
 *      |
 *      +--------------------+
 *      |                    |
 *      v                    v
 * MarketPrice            MarketTick
 *      |                    |
 *      v                    v
 * PriceUpdated          TickEngine
 *                           |
 *                           v
 *                     MarketCandle
 *                           |
 *                           +------------+
 *                           |            |
 *                           v            v
 *                  CandleRepository   EventBus
 *                           |
 *                           v
 *                       Room Database
 *
 */
@Singleton
class MarketFeedManager @Inject constructor(


    private val marketRepository: MarketRepository,


    private val candleRepository: CandleRepository,


    private val eventBus: EventBus,


    private val tickProcessor: TickProcessor


) {



    private var started = false





    fun start(

        scope: CoroutineScope

    ) {


        if (started) {

            return

        }


        started = true





        /*
         *
         * Live Price Pipeline
         *
         * MarketPrice
         *      |
         *      v
         * PriceUpdated Event
         *
         */
        scope.launch {


            marketRepository

                .observeLivePrice()

                .collectLatest { price ->



                    eventBus.publish(


                        MarketEvent.PriceUpdated(


                            marketPrice = price


                        )


                    )


                }



        }









        /*
         *
         * Tick Pipeline
         *
         * MarketTick
         *      |
         *      v
         * TickEngine
         *      |
         *      v
         * MarketCandle
         *
         *      |
         *      +----------------+
         *      |                |
         *      v                v
         * CandleRepository   EventBus
         *
         */
        scope.launch {


            marketRepository

                .observeLiveTicks()

                .collect { tick ->



                    val candles =

                        tickProcessor.process(tick)





                    candles.forEach { candle ->





                        candleRepository.saveCandles(

                            listOf(candle)

                        )






                        eventBus.publish(


                            MarketEvent.CandleClosed(


                                candle = candle


                            )


                        )



                    }



                }



        }



    }



}
