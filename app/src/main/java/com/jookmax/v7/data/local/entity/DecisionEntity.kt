package com.jookmax.v7.data.local.entity


import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(
    tableName = "decision"
)
data class DecisionEntity(


    @PrimaryKey(
        autoGenerate = true
    )
    val id: Long = 0L,


    val symbol: String,


    val action: String,


    val confidence: Double,


    val marketScore: Double,


    val riskAllowed: Boolean,


    val learningReward: Double,


    val timestamp: Long


)