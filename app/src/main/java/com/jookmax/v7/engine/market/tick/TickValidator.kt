package com.jookmax.v7.engine.market.tick

import com.jookmax.v7.core.model.MarketTick
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Validates incoming market ticks before processing.
 *
 * MarketTick
 *      |
 *      v
 * Validation
 *      |
 *      v
 * TickProcessor
 */
@Singleton
class TickValidator @Inject constructor() {


    fun isValid(
        tick: MarketTick
    ): Boolean {


        if (tick.price <= 0.0) {
            return false
        }


        if (tick.timestamp <= 0L) {
            return false
        }


        if (tick.bid != null &&
            tick.ask != null &&
            tick.bid > tick.ask
        ) {
            return false
        }


        return true

    }

}
