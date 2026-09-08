package com.jookmax.v7.engine.events.subscriber


import com.jookmax.v7.brain.market.MarketBrain

import com.jookmax.v7.core.events.EngineEvent
import com.jookmax.v7.core.events.EventSubscriber
import com.jookmax.v7.core.events.MarketEvent

import com.jookmax.v7.core.logging.Logger
import com.jookmax.v7.monitoring.RuntimeObserver

import javax.inject.Inject
import javax.inject.Singleton



/**
 * Handles market related engine events.
 *
 * Flow:
 *
 * MarketEvent
 *      ↓
 * MarketEventSubscriber
 *      ↓
 * MarketBrain
 *
 */
@Singleton
class MarketEventSubscriber @Inject constructor(


    private val logger: Logger,


    private val runtimeObserver: RuntimeObserver,


    private val marketBrain: MarketBrain


) : EventSubscriber {





    override suspend fun onEvent(

        event: EngineEvent

    ) {


        when (event) {


            is MarketEvent.PriceUpdated -> {

                handlePriceUpdate(event)

            }



            is MarketEvent.CandleClosed -> {

                handleCandleClosed(event)

            }



            is MarketEvent.CandleUpdated -> {

                handleCandleUpdate(event)

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



        val analysis = marketBrain.analyze()



        logger.info(

            tag = "MarketEventSubscriber",

            message =
                "Price updated: ${price.symbol} ${price.price} | Analysis=${analysis.status}"

        )



        runtimeObserver.observe(

            "MARKET_PRICE_UPDATED"

        )


    }








    private fun handleCandleClosed(

        event: MarketEvent.CandleClosed

    ) {


        val candle = event.candle



        logger.info(

            tag = "MarketEventSubscriber",

            message =
                "Candle closed: ${candle.symbol}"

        )



        runtimeObserver.observe(

            "CANDLE_CLOSED"

        )

    }








    private fun handleCandleUpdate(

        event: MarketEvent.CandleUpdated

    ) {


        val candle = event.candle



        logger.debug(

            tag = "MarketEventSubscriber",

            message =
                "Candle updated: ${candle.symbol}"

        )



        runtimeObserver.observe(

            "CANDLE_UPDATED"

        )

    }


}