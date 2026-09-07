package com.jookmax.v7.data.repository


import com.jookmax.v7.core.monitoring.MetricsHistory
import com.jookmax.v7.core.monitoring.PerformanceSnapshot

import com.jookmax.v7.domain.repository.MonitoringRepository

import kotlinx.coroutines.flow.StateFlow

import javax.inject.Inject
import javax.inject.Singleton



/**
 * Monitoring repository implementation.
 *
 * Data layer implementation of monitoring repository contract.
 *
 * Responsibilities:
 * - Access monitoring data source
 * - Hide MetricsHistory implementation
 * - Provide monitoring data to domain layer
 */
@Singleton
class MonitoringRepositoryImpl @Inject constructor(

    private val metricsHistory: MetricsHistory

) : MonitoringRepository {





    override fun getSnapshots():

            List<PerformanceSnapshot> {


        return metricsHistory.getHistory()


    }









    override fun getLatestSnapshot():

            PerformanceSnapshot? {


        return metricsHistory.getLatest()


    }









    override fun getSnapshotCount():

            Int {


        return metricsHistory.size()


    }









    override fun observeSnapshots():

            StateFlow<List<PerformanceSnapshot>> {


        return metricsHistory.historyFlow


    }









    override fun clearHistory() {


        metricsHistory.clear()


    }



}