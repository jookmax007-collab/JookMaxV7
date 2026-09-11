package com.jookmax.v7.brain.reward


data class RewardResult(

    val reward: Double,

    val success: Boolean,

    val reason: String,

    val timestamp: Long = System.currentTimeMillis()

)