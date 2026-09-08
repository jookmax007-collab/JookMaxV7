package com.jookmax.v7.brain.analytics


/**
 * Represents a single trading decision record.
 *
 * Used for:
 * - Decision analytics
 * - Future learning system
 * - Performance evaluation
 */
data class DecisionRecord(

    /**
     * Decision identifier.
     */
    val id: Long = 0L,


    /**
     * Market symbol.
     */
    val symbol: String,


    /**
     * Generated decision action.
     *
     * Examples:
     * BUY
     * SELL
     * HOLD
     */
    val action: String,


    /**
     * Decision confidence score.
     */
    val confidence: Double,


    /**
     * Market score used during decision.
     */
    val marketScore: Double,


    /**
     * Risk permission state.
     */
    val riskAllowed: Boolean,


    /**
     * Learning reward value at decision time.
     */
    val learningReward: Double,


    /**
     * Decision creation timestamp.
     */
    val timestamp: Long

)