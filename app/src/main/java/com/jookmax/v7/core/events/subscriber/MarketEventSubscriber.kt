package com.jookmax.v7.core.events.subscriber


import com.jookmax.v7.core.events.EngineEvent
import com.jookmax.v7.core.events.EventSubscriber
import com.jookmax.v7.core.events.MarketEvent
import com.jookmax.v7.core.logging.Logger

import javax.inject.Inject
import javax.inject.Singleton



/**
 * Handles market related engine events.
 *
 * Responsible for receiving:
 * - Price updates
 * - Candle updates
 * - Candle close events
 *
 * Connected with:
 * - Central Logger
 * - MarketBrain
 * - TechnicalAnalyzer
 * - TickEngine
 * - DecisionEngine
 */
@Singleton
class MarketEventSubscriber @Inject constructor(

    private val logger: Logger

) : EventSubscriber {





    override suspend fun onEvent(

        event: EngineEvent

    ) {


        when (event) {



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


        val price =

            event.marketPrice



        logger.info(

            tag = "MarketEventSubscriber",

            message =
                "Price updated: ${price.symbol} ${price.price}"

        )



        // Future:
        // Send price update to MarketBrain
        // Update indicators
        // Trigger analysis pipeline


    }









    private fun handleCandleClosed(

        event: MarketEvent.CandleClosed

    ) {


        val candle =

            event.candle



        logger.info(

            tag = "MarketEventSubscriber",

            message =
                "Candle closed: ${candle.symbol}"

        )



        // Future:
        // Update technical analysis
        // Run decision pipeline


    }









    private fun handleCandleUpdate(

        event: MarketEvent.CandleUpdated

    ) {


        val candle =

            event.candle



        logger.debug(

            tag = "MarketEventSubscriber",

            message =
                "Candle updated: ${candle.symbol}"

        )



        // Future:
        // Live chart update
        // Tick aggregation


    }



}