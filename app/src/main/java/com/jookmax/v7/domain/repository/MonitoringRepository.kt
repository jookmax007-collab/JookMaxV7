package com.jookmax.v7.domain.repository


import com.jookmax.v7.core.monitoring.PerformanceSnapshot

import kotlinx.coroutines.flow.StateFlow



/**
 * Monitoring repository contract.
 *
 * Domain layer abstraction.
 *
 * Implementations:
 * - MonitoringRepositoryImpl
 */
interface MonitoringRepository {



    fun getSnapshots():

            List<PerformanceSnapshot>




    fun getLatestSnapshot():

            PerformanceSnapshot?




    fun getSnapshotCount():

            Int




    fun observeSnapshots():

            StateFlow<List<PerformanceSnapshot>>




    fun clearHistory()



}