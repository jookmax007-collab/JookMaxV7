package com.jookmax.v7.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Index


@Entity(
    tableName = "decision_patterns",
    indices = [
        Index(value = ["patternName"]),
        Index(value = ["marketRegime"]),
        Index(value = ["timestamp"])
    ]
)
data class DecisionPatternEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val patternName: String,

    val marketRegime: String,

    val trendState: String,

    val volatilityState: String,

    val successfulCount: Int,

    val failedCount: Int,

    val averageReward: Double,

    val confidenceScore: Double,

    val usageCount: Long,

    val brainVersion: String,

    val timestamp: Long
)
