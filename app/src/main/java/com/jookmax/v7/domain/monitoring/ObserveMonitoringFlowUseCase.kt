package com.jookmax.v7.domain.monitoring


import com.jookmax.v7.core.monitoring.PerformanceSnapshot
import com.jookmax.v7.core.monitoring.MonitoringRepository

import kotlinx.coroutines.flow.StateFlow

import javax.inject.Inject



/**
 * Observes live monitoring data stream.
 *
 * Domain layer entry point for reactive monitoring.
 *
 * Responsibility:
 * - Expose monitoring history flow
 * - Hide repository implementation details
 * - Prepare live dashboard integration
 *
 * Architecture:
 *
 * MonitoringRepository
 *          |
 *          v
 * ObserveMonitoringFlowUseCase
 *          |
 *          v
 * MonitoringViewModel
 */
class ObserveMonitoringFlowUseCase @Inject constructor(

    private val monitoringRepository: MonitoringRepository

) {



    operator fun invoke():

            StateFlow<List<PerformanceSnapshot>> {


        return monitoringRepository.observeSnapshots()


    }


}