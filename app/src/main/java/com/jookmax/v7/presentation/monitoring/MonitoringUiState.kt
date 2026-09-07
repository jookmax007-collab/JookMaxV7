package com.jookmax.v7.presentation.monitoring


import com.jookmax.v7.core.monitoring.PerformanceSnapshot
import com.jookmax.v7.domain.monitoring.PerformanceReport



/**
 * Represents monitoring screen state.
 *
 * Used by:
 * - MonitoringViewModel
 * - Future Compose UI
 *
 * Contains:
 * - Analytics report
 * - Reactive monitoring snapshots
 */
sealed class MonitoringUiState {





    /**
     * Initial/loading state.
     */
    data object Loading : MonitoringUiState()







    /**
     * Data available state.
     *
     * Contains:
     * - Performance analytics report
     * - Live monitoring history
     */
    data class Available(

        val report: PerformanceReport,

        val snapshots: List<PerformanceSnapshot> = emptyList()

    ) : MonitoringUiState()







    /**
     * Error state.
     */
    data class Error(

        val message: String

    ) : MonitoringUiState()



}