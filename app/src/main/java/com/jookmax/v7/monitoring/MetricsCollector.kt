package com.jookmax.v7.monitoring


import javax.inject.Inject
import javax.inject.Singleton



/**
 * Collects runtime metrics for JookMax engine.
 *
 * Responsible for:
 * - Event counting
 * - Error counting
 * - Runtime statistics
 *
 * Does not create snapshots.
 * Snapshot creation belongs to EngineMonitor.
 */
@Singleton
class MetricsCollector @Inject constructor() {



    private var processedEvents: Long = 0


    private var failedEvents: Long = 0


    private var totalProcessingTimeMs: Long = 0





    fun recordEvent(

        processingTimeMs: Long = 0

    ) {


        processedEvents++


        totalProcessingTimeMs += processingTimeMs


    }







    fun recordFailure() {


        failedEvents++


    }







    fun getProcessedEvents(): Long {


        return processedEvents


    }







    fun getFailedEvents(): Long {


        return failedEvents


    }







    fun getAverageLatencyMs(): Long {


        if (processedEvents == 0L) {

            return 0

        }


        return totalProcessingTimeMs / processedEvents


    }







    fun reset() {


        processedEvents = 0


        failedEvents = 0


        totalProcessingTimeMs = 0


    }



}
