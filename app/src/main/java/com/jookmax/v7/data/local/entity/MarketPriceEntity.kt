package com.jookmax.v7.data.local.entity


import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(
    tableName = "market_price"
)
data class MarketPriceEntity(


    @PrimaryKey
    val symbol: String,


    val price: Double,


    val timestamp: Long,


    val bid: Double?,


    val ask: Double?

)