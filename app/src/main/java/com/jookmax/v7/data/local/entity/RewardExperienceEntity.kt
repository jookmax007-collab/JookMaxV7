package com.jookmax.v7.data.local.entity


import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(
    tableName = "reward_experiences"
)
data class RewardExperienceEntity(


    @PrimaryKey(
        autoGenerate = true
    )
    val id: Long = 0,


    val action: String,


    val entryPrice: Double,


    val exitPrice: Double,


    val reward: Double,


    val success: Boolean,


    val reason: String,



    val confidence: Double,


    val positionSize: Double,


    val stopLoss: Double,


    val takeProfit: Double,


    val symbol: String,


    val trend: String,


    val rsi: Double,


    val movingAverage: Double,


    val volatility: Double,



    val timestamp: Long

)