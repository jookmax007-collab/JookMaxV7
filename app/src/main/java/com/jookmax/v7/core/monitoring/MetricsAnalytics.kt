package com.jookmax.v7.core.monitoring


import javax.inject.Inject
import javax.inject.Singleton



/**
 * Provides analytical calculations based on engine metrics history.
 *
 * Responsible for:
 * - Performance analysis
 * - Runtime statistics
 * - Health metrics calculation
 *
 * Future connections:
 * - Performance Center UI
 * - Optimization Engine
 * - Learning System
 */
@Singleton
class MetricsAnalytics @Inject constructor(

    private val metricsHistory: MetricsHistory

) {




    fun getTotalProcessedEvents(): Long {


        return metricsHistory
            .getHistory()
            .sumOf {

                it.processedEvents

            }


    }







    fun getTotalFailedEvents(): Long {


        return metricsHistory
            .getHistory()
            .sumOf {

                it.failedEvents

            }


    }







    fun getAverageLatency(): Long {


        val history =
            metricsHistory.getHistory()



        if (history.isEmpty()) {


            return 0L


        }



        return history
            .map {

                it.processingLatencyMs

            }
            .average()
            .toLong()


    }







    fun getSnapshotCount(): Int {


        return metricsHistory
            .size()


    }







    fun getFailureRate(): Double {


        val totalProcessed =
            getTotalProcessedEvents()



        val totalFailed =
            getTotalFailedEvents()



        val total =
            totalProcessed + totalFailed



        if (total == 0L) {


            return 0.0


        }



        return totalFailed.toDouble() /
                total.toDouble()


    }







    fun getLatestState(): String {


        return metricsHistory
            .getLatest()
            ?.engineState
            ?: "UNKNOWN"


    }







    fun reset() {


        // Analytics layer has no internal state.
        // History owner manages stored data.


    }



}