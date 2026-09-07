package com.jookmax.v7.core.logging



/**
 * Central logging contract for JookMax system.
 *
 * All modules should depend on this interface,
 * not on the concrete logger implementation.
 *
 * Future:
 * - Database logging
 * - Remote logging
 * - Monitoring integration
 */
interface Logger {



    /**
     * Debug information.
     */
    fun debug(
        tag: String,
        message: String
    )



    /**
     * General application information.
     */
    fun info(
        tag: String,
        message: String
    )



    /**
     * Warning situations.
     */
    fun warning(
        tag: String,
        message: String
    )



    /**
     * Error situations.
     */
    fun error(
        tag: String,
        message: String,
        throwable: Throwable? = null
    )


}