package com.jookmax.v7.core.monitoring


import javax.inject.Inject
import javax.inject.Singleton



/**
 * Repository layer for monitoring data access.
 *
 * Responsible for:
 * - Providing monitoring data to upper layers
 * - Hiding storage implementation details
 * - Preparing future database integration
 *
 * Future connections:
 * - Room database
 * - Remote monitoring sync
 * - Export system
 */
@Singleton
class MonitoringRepository @Inject constructor(

    private val metricsHistory: MetricsHistory

) {




    fun getSnapshots():

            List<PerformanceSnapshot> {


        return metricsHistory.getHistory()


    }







    fun getLatestSnapshot():

            PerformanceSnapshot? {


        return metricsHistory.getLatest()


    }







    fun getSnapshotCount():

            Int {


        return metricsHistory.size()


    }







    fun clearHistory() {


        metricsHistory.clear()


    }



}