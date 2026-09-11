package com.jookmax.v7.engine.events.subscriber


import com.jookmax.v7.brain.BrainManager
import com.jookmax.v7.brain.market.MarketBrain

import com.jookmax.v7.core.event.EngineEvent
import com.jookmax.v7.core.event.EventSubscriber
import com.jookmax.v7.core.event.MarketEvent

import com.jookmax.v7.core.logging.Logger

import com.jookmax.v7.monitoring.RuntimeObserver
import com.jookmax.v7.monitoring.EngineMonitor

import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class MarketEventSubscriber @Inject constructor(


    private val logger: Logger,


    private val runtimeObserver: RuntimeObserver,


    private val marketBrain: MarketBrain,


    private val brainManager: BrainManager,


    private val engineMonitor: EngineMonitor


) : EventSubscriber {





    override suspend fun onEvent(

        event: EngineEvent

    ) {


        when(event) {


            is MarketEvent.PriceUpdated -> {


                handlePriceUpdate(

                    event

                )


            }



            is MarketEvent.CandleClosed -> {


                handleCandleClosed(

                    event

                )


            }



            is MarketEvent.CandleUpdated -> {


                handleCandleUpdate(

                    event

                )


            }



            else -> Unit


        }


    }









    private fun handlePriceUpdate(

        event: MarketEvent.PriceUpdated

    ) {


        val price = event.marketPrice




        marketBrain.updateMarket(

            price

        )



        engineMonitor.updateMarketPrice(

            price

        )





        logger.info(

            tag = "MarketEventSubscriber",

            message =
                "Market price updated: ${price.symbol} ${price.price}"

        )





        runtimeObserver.observe(

            "MARKET_PRICE_UPDATED"

        )


    }









    private suspend fun handleCandleClosed(

        event: MarketEvent.CandleClosed

    ) {


        val candle = event.candle





        logger.info(

            tag = "MarketEventSubscriber",

            message =
                "Candle closed: ${candle.symbol}"

        )





        brainManager.process(

            candle

        )





        runtimeObserver.observe(

            "CANDLE_CLOSED"

        )


    }









    private fun handleCandleUpdate(

        event: MarketEvent.CandleUpdated

    ) {


        logger.debug(

            tag = "MarketEventSubscriber",

            message =
                "Candle updated: ${event.candle.symbol}"

        )


    }


}