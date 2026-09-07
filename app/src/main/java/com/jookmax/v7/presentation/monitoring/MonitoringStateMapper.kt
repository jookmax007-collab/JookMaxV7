package com.jookmax.v7.presentation.monitoring


import com.jookmax.v7.domain.monitoring.PerformanceReport

import javax.inject.Inject
import javax.inject.Singleton



/**
 * Maps domain monitoring models to presentation UI states.
 *
 * Responsibility:
 * - Keep ViewModel clean
 * - Convert domain data into UI representation
 *
 * Architecture:
 *
 * Domain
 *   |
 *   v
 * PerformanceReport
 *   |
 *   v
 * MonitoringStateMapper
 *   |
 *   v
 * MonitoringUiState
 */
@Singleton
class MonitoringStateMapper @Inject constructor() {



    fun map(

        report: PerformanceReport

    ): MonitoringUiState {



        return MonitoringUiState.Available(

            report = report

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
