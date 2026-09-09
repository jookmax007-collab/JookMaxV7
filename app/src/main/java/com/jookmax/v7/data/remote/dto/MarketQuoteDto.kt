package com.jookmax.v7.data.remote.dto


data class MarketQuoteDto(

    val symbol: String,

    val bid: Double,

    val ask: Double,

    val timestamp: Long

)
