package com.jookmax.v7.core.logging


/**
 * Storage contract for application logs.
 *
 * Implementations can provide:
 * - In-memory storage
 * - Database storage
 * - Remote storage
 */
interface LogStorage {


    fun save(
        entry: LogEntry
    )


    fun getLogs(): List<LogEntry>


    fun clear()

}