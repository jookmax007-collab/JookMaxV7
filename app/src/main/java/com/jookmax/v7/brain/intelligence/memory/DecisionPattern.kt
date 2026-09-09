package com.jookmax.v7.brain.intelligence.memory


import com.jookmax.v7.brain.decision.DecisionAction



/**
 * Historical decision pattern stored in intelligence memory.
 *
 * Represents one past brain decision experience.
 *
 * Flow:
 *
 * Market State
 *      |
 *      v
 * Decision
 *      |
 *      v
 * Result
 *      |
 *      v
 * Memory Pattern
 *
 */
data class DecisionPattern(


    /**
     * Trading symbol
     *
     * Example:
     * XAUUSD
     */
    val symbol: String,



    /**
     * Market trend at decision time
     *
     * BULLISH
     * BEARISH
     * SIDEWAYS
     */
    val trend: String,



    /**
     * RSI value when decision was generated
     */
    val rsi: Double,



    /**
     * Market volatility
     */
    val volatility: Double,



    /**
     * Brain decision action
     */
    val action: DecisionAction,



    /**
     * Intelligence confidence
     */
    val confidence: Double,



    /**
     * Final validation result
     */
    val approved: Boolean,



    /**
     * Real trade result reward
     *
     * Positive = success
     * Negative = failure
     */
    val reward: Double,



    /**
     * Creation timestamp
     */
    val timestamp: Long = System.currentTimeMillis()

)