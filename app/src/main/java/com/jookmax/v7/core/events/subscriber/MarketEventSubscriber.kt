package com.jookmax.v7.core.events.subscriber


import com.jookmax.v7.core.events.EngineEvent
import com.jookmax.v7.core.events.EventSubscriber
import com.jookmax.v7.core.events.MarketEvent
import javax.inject.Inject


/**
 * Handles market related engine events.
 *
 * Responsible for receiving:
 * - Price updates
 * - Candle updates
 * - Candle close events
 *
 * Future connections:
 * - MarketBrain
 * - TechnicalAnalyzer
 * - TickEngine
 * - DecisionEngine
 */
class MarketEventSubscriber @Inject constructor(

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


        // Future:
        // Update technical analysis
        // Run decision pipeline


    }





    private fun handleCandleUpdate(
        event: MarketEvent.CandleUpdated
    ) {


        val candle =
            event.candle


        // Future:
        // Live chart update
        // Tick aggregation


    }


}