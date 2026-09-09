package com.jookmax.v7.brain.confidence


data class ConfidenceFeedback(

    val decision: String,

    val initialConfidence: Double,

    val reward: Double,

    val success: Boolean,

    val timestamp: Long = System.currentTimeMillis()

)
