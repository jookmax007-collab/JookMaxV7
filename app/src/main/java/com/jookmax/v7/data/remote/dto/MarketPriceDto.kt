package com.jookmax.v7.data.remote.dto



data class MarketPriceDto(


    val symbol: String,


    val price: Double,


    val timestamp: Long,


    val bid: Double = 0.0,


    val ask: Double = 0.0


)
