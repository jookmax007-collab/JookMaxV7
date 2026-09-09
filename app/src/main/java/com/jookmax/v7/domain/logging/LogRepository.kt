package com.jookmax.v7.domain.logging


import com.jookmax.v7.core.logging.LogEntry



/**
 * Domain contract for log management.
 *
 * Domain layer depends on this abstraction.
 *
 * Implementations belong to data layer.
 */
interface LogRepository {



    /**
     * Store a log entry.
     */
    fun save(
        entry: LogEntry
    )



    /**
     * Retrieve application logs.
     */
    fun getLogs(): List<LogEntry>



    /**
     * Clear stored logs.
     */
    fun clear()



}
