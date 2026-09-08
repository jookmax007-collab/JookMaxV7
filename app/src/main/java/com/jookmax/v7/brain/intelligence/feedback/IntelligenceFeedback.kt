package com.jookmax.v7.brain.intelligence.feedback


import com.jookmax.v7.brain.decision.DecisionAction



/**
 * Intelligence Feedback
 *
 * Stores final result of intelligence decision.
 *
 * Decision
 *      |
 *      v
 * Market Result
 *      |
 *      v
 * Feedback Memory
 *
 */
data class IntelligenceFeedback(


    val action: DecisionAction,


    val confidence: Double,


    val reward: Double,


    val success: Boolean,


    val timestamp: Long =

        System.currentTimeMillis()

)