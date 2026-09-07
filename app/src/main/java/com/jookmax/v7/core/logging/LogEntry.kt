package com.jookmax.v7.core.logging



/**
 * Represents a single log record inside JookMax system.
 *
 * Future usage:
 * - Room persistence
 * - Debug dashboard
 * - Monitoring
 * - Analytics
 */
data class LogEntry(


    /**
     * Unique log identifier.
     */
    val id: Long = 0L,



    /**
     * Log severity level.
     */
    val level: LogLevel,



    /**
     * Source component name.
     *
     * Examples:
     * Engine
     * BrainManager
     * MarketFeed
     */
    val tag: String,



    /**
     * Human readable message.
     */
    val message: String,



    /**
     * Creation timestamp.
     */
    val timestamp: Long,



    /**
     * Optional exception information.
     */
    val throwable: Throwable? = null


)