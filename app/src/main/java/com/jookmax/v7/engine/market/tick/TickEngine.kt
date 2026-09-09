package com.jookmax.v7.engine.market.tick


import com.jookmax.v7.core.model.MarketCandle
import com.jookmax.v7.core.model.MarketTick

import javax.inject.Inject
import javax.inject.Singleton



/**
 * Core Tick processing engine.
 *
 * Responsibilities:
 *
 * MarketTick
 *      |
 *      v
 * CandleBuilder
 *      |
 *      v
 * MarketCandle
 *
 */
@Singleton
class TickEngine @Inject constructor() {



    private val builders =
        mutableMapOf<CandleInterval, CandleBuilder>()



    init {

        CandleInterval.entries.forEach { interval ->

            builders[interval] =
                CandleBuilder(interval)

        }

    }





    /**
     * Processes incoming market tick.
     *
     * Returns closed candles.
     */
    fun process(
        tick: MarketTick
    ): List<MarketCandle> {


        val closedCandles =
            mutableListOf<MarketCandle>()



        builders.values.forEach { builder ->


            val candle =
                builder.update(tick)



            if (candle != null) {

                closedCandles.add(candle)

            }


        }



        return closedCandles

    }



}
