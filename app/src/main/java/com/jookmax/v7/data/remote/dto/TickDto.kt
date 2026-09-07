package com.jookmax.v7.data.remote.dto


data class TickDto(

    val symbol: String,

    val price: Double,

    val timestamp: Long,

    val volume: Double? = null

)