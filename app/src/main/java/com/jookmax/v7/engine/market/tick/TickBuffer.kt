package com.jookmax.v7.engine.market.tick


import com.jookmax.v7.core.model.MarketTick


/**
 * Temporary in-memory storage for incoming market ticks.
 *
 * Responsible only for buffering ticks
 * before processing.
 */
class TickBuffer {


    private val ticks =
        mutableListOf<MarketTick>()



    fun add(
        tick: MarketTick
    ) {

        ticks.add(tick)

    }



    fun getAll(): List<MarketTick> {

        return ticks.toList()

    }



    fun latest(): MarketTick? {

        return ticks.lastOrNull()

    }



    fun size(): Int {

        return ticks.size

    }



    fun clear() {

        ticks.clear()

    }


}