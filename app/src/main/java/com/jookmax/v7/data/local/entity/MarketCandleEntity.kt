package com.jookmax.v7.data.local.entity


import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(
    tableName = "market_candle"
)
data class MarketCandleEntity(


    @PrimaryKey
    val id: Long,


    val symbol: String,


    val timeframe: Int,


    val timestamp: Long,


    val open: Double,


    val high: Double,


    val low: Double,


    val close: Double,


    val volume: Double?

)
