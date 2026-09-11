package com.jookmax.v7.brain.reward.model


import com.jookmax.v7.brain.decision.DecisionAction



data class RewardExperience(


    val id: Long = 0,


    val action: DecisionAction,


    val entryPrice: Double,


    val exitPrice: Double,


    val reward: Double,


    val success: Boolean,


    val reason: String,


    // Intelligence snapshot

    val confidence: Double,


    val positionSize: Double,


    val stopLoss: Double,


    val takeProfit: Double,


    val symbol: String,


    val trend: String,


    val rsi: Double,


    val movingAverage: Double,


    val volatility: Double,


    val timestamp: Long = System.currentTimeMillis()

)