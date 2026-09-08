package com.jookmax.v7.engine.market


import com.jookmax.v7.core.events.EventBus
import com.jookmax.v7.core.events.MarketEvent
import com.jookmax.v7.domain.repository.MarketRepository

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
 *      v
 * MarketRepository
 *      |
 *      v
 * MarketFeedManager
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


    private val eventBus: EventBus


) {



    private var started = false





    fun start(

        scope: CoroutineScope

    ) {


        if (started) {

            return

        }


        started = true





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



    }





}