package com.jookmax.v7.domain.monitoring



/**
 * Represents analyzed engine performance information.
 *
 * Contains:
 * - Runtime metrics
 * - Engine health metrics
 * - Decision analytics
 */
data class PerformanceReport(


    val totalProcessedEvents: Long,


    val totalFailedEvents: Long,


    val averageLatencyMs: Long,


    val failureRate: Double,


    val currentEngineState: String,



    /**
     * Total generated trading decisions.
     */
    val totalDecisions: Long,



    /**
     * Number of BUY decisions.
     */
    val buyDecisions: Long,



    /**
     * Number of SELL decisions.
     */
    val sellDecisions: Long,



    /**
     * Number of HOLD decisions.
     */
    val holdDecisions: Long,



    /**
     * Average confidence of generated decisions.
     */
    val averageDecisionConfidence: Double


)