package com.jookmax.v7.core.model


/**
 * Represents current market quote state.
 */
data class MarketQuote(

    val symbol: Symbol,

    val bid: Double,

    val ask: Double,

    val last: Double,

    val timestamp: Long

)