package com.jookmax.v7.presentation.monitoring


import com.jookmax.v7.domain.monitoring.PerformanceReport
import com.jookmax.v7.monitoring.PerformanceSnapshot

import javax.inject.Inject
import javax.inject.Singleton



/**
 * Maps domain monitoring models to presentation UI states.
 */
@Singleton
class MonitoringStateMapper @Inject constructor() {



    fun map(

        report: PerformanceReport,

        snapshots: List<PerformanceSnapshot> = emptyList()

    ): MonitoringUiState {



        return MonitoringUiState.Available(

            report = report,

            snapshots = snapshots

        )


    }







    fun mapError(

        throwable: Throwable

    ): MonitoringUiState {



        return MonitoringUiState.Error(

            message =
                throwable.message
                    ?: "Unknown monitoring error"

        )


    }



}