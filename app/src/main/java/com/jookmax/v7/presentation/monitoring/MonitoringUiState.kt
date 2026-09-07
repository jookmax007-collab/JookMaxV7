package com.jookmax.v7.presentation.monitoring


import com.jookmax.v7.domain.monitoring.PerformanceReport



/**
 * Represents monitoring screen state.
 *
 * Used by:
 * - MonitoringViewModel
 * - Future Compose UI
 */
sealed class MonitoringUiState {



    /**
     * Initial/loading state.
     */
    data object Loading : MonitoringUiState()






    /**
     * Data available state.
     */
    data class Available(

        val report: PerformanceReport

    ) : MonitoringUiState()






    /**
     * Error state.
     */
    data class Error(

        val message: String

    ) : MonitoringUiState()



}