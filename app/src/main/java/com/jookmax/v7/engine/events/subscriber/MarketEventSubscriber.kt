package com.jookmax.v7.engine.events.subscriber


import com.jookmax.v7.brain.BrainManager
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
 *      |
 *      v
 * MarketEventSubscriber
 *      |
 *      v
 * MarketBrain
 *      |
 *      v
 * Brain Pipeline
 *
 *
 * Candle Flow:
 *
 * CandleClosed
 *      |
 *      v
 * MarketBrain.updateCandle()
 *      |
 *      v
 * TechnicalAnalyzer
 *
 */
@Singleton
class MarketEventSubscriber @Inject constructor(


    private val logger: Logger,


    private val runtimeObserver: RuntimeObserver,


    private val marketBrain: MarketBrain,


    private val brainManager: BrainManager


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





        logger.info(

            tag = "MarketEventSubscriber",

            message =
                "Market price updated: ${price.symbol} ${price.price}"

        )





        runtimeObserver.observe(

            "MARKET_PRICE_UPDATED"

        )





        /*
            Decision Pipeline

            MarketBrain
                |
                v
            RiskBrain
                |
                v
            DecisionEngine
                |
                v
            DecisionEvent
        */


        brainManager.process()



    }









    private fun handleCandleClosed(

        event: MarketEvent.CandleClosed

    ) {


        val candle = event.candle





        /*
            Candle Stream

            CandleClosed
                  |
                  v
            MarketBrain
                  |
                  v
            TechnicalAnalyzer
        */


        marketBrain.updateCandle(

            candle

        )





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