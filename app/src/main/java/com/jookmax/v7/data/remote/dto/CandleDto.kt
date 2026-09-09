package com.jookmax.v7.data.remote.dto


data class CandleDto(

    val symbol: String,

    val timeframe: Int,

    val timestamp: Long,

    val open: Double,

    val high: Double,

    val low: Double,

    val close: Double,

    val volume: Double? = null

)
