package com.jookmax.v7.core.monitoring


/**
 * Represents current health status of JookMax engine.
 *
 * Used by:
 * - EngineMonitor
 * - Live Monitor UI
 * - Performance analytics
 * - Health checks
 */
sealed class EngineHealth {



    /**
     * Engine is operating normally.
     */
    data object Healthy : EngineHealth()





    /**
     * Engine is running but needs attention.
     */
    data class Warning(

        val message: String

    ) : EngineHealth()





    /**
     * Engine encountered a failure.
     */
    data class Error(

        val message: String,

        val throwable: Throwable? = null

    ) : EngineHealth()





    /**
     * Engine has not started yet.
     */
    data object Offline : EngineHealth()



}