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
 * Responsibilities:
 *
 * - Receive market events
 * - Update MarketBrain
 * - Notify runtime monitoring
 * - Prepare pipeline for:
 *      Technical Analysis
 *      Risk Evaluation
 *      Decision Engine
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



        /*
         Future pipeline:

         1. TechnicalAnalyzer
         2. MarketBrain.analyze()
         3. RiskBrain.evaluateRisk()
         4. DecisionEngine.decide()
         5. DecisionAnalytics persistence

        */



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