package com.jookmax.v7.monitoring


import com.jookmax.v7.domain.repository.MonitoringRepository

import javax.inject.Inject
import javax.inject.Singleton



/**
 * Provides analytical calculations based on engine monitoring data.
 *
 * Responsible for:
 * - Performance analysis
 * - Runtime statistics
 * - Health metrics calculation
 *
 * Data source:
 * - MonitoringRepository contract
 *
 * Future connections:
 * - Performance Center UI
 * - Optimization Engine
 * - Learning System
 */
@Singleton
class MetricsAnalytics @Inject constructor(

    private val monitoringRepository: MonitoringRepository

) {





    fun getTotalProcessedEvents(): Long {


        return monitoringRepository
            .getSnapshots()
            .sumOf {


                it.processedEvents


            }


    }









    fun getTotalFailedEvents(): Long {


        return monitoringRepository
            .getSnapshots()
            .sumOf {


                it.failedEvents


            }


    }









    fun getAverageLatency(): Long {


        val snapshots =

            monitoringRepository
                .getSnapshots()



        if (snapshots.isEmpty()) {


            return 0L


        }



        return snapshots
            .map {


                it.processingLatencyMs


            }
            .average()
            .toLong()


    }









    fun getSnapshotCount(): Int {


        return monitoringRepository
            .getSnapshotCount()


    }









    fun getFailureRate(): Double {


        val processed =

            getTotalProcessedEvents()



        val failed =

            getTotalFailedEvents()



        val total =

            processed + failed





        if (total == 0L) {


            return 0.0


        }





        return failed.toDouble() /

                total.toDouble()


    }









    fun getLatestState(): String {


        return monitoringRepository
            .getLatestSnapshot()
            ?.engineState
            ?: "UNKNOWN"


    }









    fun reset() {


        // Analytics layer has no internal state.
        // Repository owns data management.


    }



}
