package com.jookmax.v7.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(
    tableName = "decision_memory",
    indices = [
        androidx.room.Index(
            value = [
                "symbol",
                "trend"
            ]
        ),
        androidx.room.Index(
            value = [
                "timestamp"
            ]
        )
    ]
)
data class DecisionMemoryEntity(


    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,


    val symbol: String,


    val trend: String,


    val rsi: Double,


    val volatility: Double,


    val action: String,


    val confidence: Double,


    val approved: Boolean,


    val reward: Double,


    val timestamp: Long

)
