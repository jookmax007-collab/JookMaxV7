package com.jookmax.v7.domain.monitoring



/**
 * Represents analyzed engine performance information.
 */
data class PerformanceReport(


    val totalProcessedEvents: Long,


    val totalFailedEvents: Long,


    val averageLatencyMs: Long,


    val failureRate: Double,


    val currentEngineState: String


)