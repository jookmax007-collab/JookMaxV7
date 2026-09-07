package com.jookmax.v7.core.events

import com.jookmax.v7.core.model.Candle
import com.jookmax.v7.core.model.MarketPrice

/**
 * Represents market related events.
 */
sealed class MarketEvent(
    override val type: EventType = EventType.MARKET,
    override val metadata: EventMetadata = EventMetadata(
        source = "Market",
        timestamp = System.currentTimeMillis()
    )
) : EngineEvent {


    data class PriceUpdated(
        val marketPrice: MarketPrice
    ) : MarketEvent()


    data class CandleClosed(
        val candle: Candle
    ) : MarketEvent()


    data class CandleUpdated(
        val candle: Candle
    ) : MarketEvent()
}