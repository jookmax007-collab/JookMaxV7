package com.jookmax.v7.core.monitoring


import kotlinx.coroutines.flow.StateFlow

import javax.inject.Inject
import javax.inject.Singleton



/**
 * Repository layer for monitoring data access.
 *
 * Responsible for:
 * - Providing monitoring data to upper layers
 * - Hiding storage implementation details
 * - Exposing reactive monitoring updates
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









    /**
     * Observe live monitoring history updates.
     *
     * Source:
     * MetricsHistory StateFlow
     *
     * Used by:
     * - Monitoring use cases
     * - ViewModel
     * - Future live dashboard
     */
    fun observeSnapshots():

            StateFlow<List<PerformanceSnapshot>> {


        return metricsHistory.historyFlow


    }









    fun clearHistory() {


        metricsHistory.clear()


    }



}