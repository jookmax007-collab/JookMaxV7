package com.jookmax.v7.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(
    tableName = "decision_memory"
)
data class DecisionMemoryEntity(


    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,


    /**
     * Trading symbol
     *
     * Example:
     * XAUUSD
     */
    val symbol: String,


    /**
     * Market trend
     */
    val trend: String,


    /**
     * RSI value
     */
    val rsi: Double,


    /**
     * Market volatility
     */
    val volatility: Double,


    /**
     * Brain decision action
     */
    val action: String,


    /**
     * Intelligence confidence
     */
    val confidence: Double,


    /**
     * Validation result
     */
    val approved: Boolean,


    /**
     * Reward after result
     */
    val reward: Double,


    /**
     * Creation timestamp
     */
    val timestamp: Long

)
