package com.jookmax.v7.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Index


@Entity(
    tableName = "learning_experiences",
    indices = [
        Index(value = ["timestamp"]),
        Index(value = ["symbol"]),
        Index(value = ["marketRegime"]),
        Index(value = ["brainVersion"])
    ]
)
data class LearningExperienceEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val decision: String,

    val confidence: Double,

    val riskApproved: Boolean,

    val positionSize: Double,

    val riskScore: Double,

    val symbol: String,

    val price: Double,

    val timeframe: String,

    val marketRegime: String,

    val trendState: String,

    val volatilityState: String,

    val rsi: Double,

    val macd: Double,

    val movingAverage: Double,

    val atr: Double,

    val supportLevel: Double,

    val resistanceLevel: Double,

    val reward: Double,

    val profitLoss: Double,

    val success: Boolean,

    val holdingTime: Long,

    val drawdown: Double,

    val schemaVersion: Int,

    val brainVersion: String,

    val strategyVersion: String,

    val featureVersion: String,

    val timestamp: Long
)
