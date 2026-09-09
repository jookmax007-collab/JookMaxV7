package com.jookmax.v7.monitoring


/**
 * Represents a snapshot of JookMax engine performance.
 *
 * Used for:
 * - Runtime analytics
 * - Monitoring dashboard
 * - Performance history
 * - Decision analytics visualization
 */
data class PerformanceSnapshot(


    /**
     * Snapshot creation time.
     */
    val timestamp: Long,



    /**
     * Current engine state.
     */
    val engineState: String,



    /**
     * Number of processed events.
     */
    val processedEvents: Long = 0,



    /**
     * Number of failed events.
     */
    val failedEvents: Long = 0,



    /**
     * Current active brain modules.
     */
    val activeBrains: Int = 0,



    /**
     * Processing latency in milliseconds.
     */
    val processingLatencyMs: Long = 0,



    /**
     * Total generated decisions.
     */
    val totalDecisions: Long = 0,



    /**
     * BUY decision count.
     */
    val buyDecisions: Long = 0,



    /**
     * SELL decision count.
     */
    val sellDecisions: Long = 0,



    /**
     * HOLD decision count.
     */
    val holdDecisions: Long = 0,



    /**
     * Average confidence of decisions.
     */
    val averageDecisionConfidence: Double = 0.0,



    /**
     * Additional metadata for future expansion.
     */
    val metadata: Map<String, String> = emptyMap()


)
