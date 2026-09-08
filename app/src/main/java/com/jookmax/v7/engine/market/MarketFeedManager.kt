package com.jookmax.v7.engine.market


import com.jookmax.v7.core.events.EventBus
import com.jookmax.v7.core.events.MarketEvent
import com.jookmax.v7.domain.repository.MarketRepository
import com.jookmax.v7.engine.market.tick.TickEngine

import kotlinx.coroutines.CoroutineScope
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
 *                           v
 *                    CandleClosed
 *
 *      |
 *      v
 * EventBus
 *      |
 *      v
 * MarketEventSubscriber
 *      |
 *      v
 * MarketBrain
 *
 */
@Singleton
class MarketFeedManager @Inject constructor(


    private val marketRepository: MarketRepository,


    private val eventBus: EventBus,


    private val tickEngine: TickEngine


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
         *      |
         *      v
         * CandleClosed Event
         *
         */
        scope.launch {


            marketRepository

                .observeLiveTicks()

                .collect { tick ->



                    val candles =

                        tickEngine.process(tick)





                    candles.forEach { candle ->



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