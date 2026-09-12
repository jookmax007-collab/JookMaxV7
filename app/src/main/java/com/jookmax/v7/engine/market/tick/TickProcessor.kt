package com.jookmax.v7.engine.market.tick

import com.jookmax.v7.core.model.MarketCandle
import com.jookmax.v7.core.model.MarketTick
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Processes incoming market ticks.
 *
 * Flow:
 *
 * MarketTick
 *      |
 *      v
 * TickValidator
 *      |
 *      v
 * TickBuffer
 *      |
 *      v
 * TickEngine
 *      |
 *      v
 * MarketCandle
 */
@Singleton
class TickProcessor @Inject constructor(

    private val tickValidator: TickValidator,

    private val tickEngine: TickEngine

) {


    private val tickBuffer =
        TickBuffer()



    fun process(
        tick: MarketTick
    ): List<MarketCandle> {


        if (!tickValidator.isValid(tick)) {

            return emptyList()

        }


        tickBuffer.add(tick)


        return tickEngine.process(tick)

    }

}
