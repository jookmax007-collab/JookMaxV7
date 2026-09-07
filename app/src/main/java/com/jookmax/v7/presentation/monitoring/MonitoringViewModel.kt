package com.jookmax.v7.presentation.monitoring


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.jookmax.v7.domain.monitoring.GetPerformanceReportUseCase

import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

import kotlinx.coroutines.launch

import javax.inject.Inject



/**
 * ViewModel bridge for monitoring presentation layer.
 *
 * Responsible for:
 * - Executing monitoring use cases
 * - Managing UI state
 * - Handling screen actions
 * - Exposing monitoring data to UI
 *
 * Architecture:
 *
 * UI
 *  |
 *  v
 * MonitoringAction
 *  |
 *  v
 * MonitoringViewModel
 *  |
 *  v
 * MonitoringStateMapper
 *  |
 *  v
 * MonitoringUiState
 *  |
 *  v
 * GetPerformanceReportUseCase
 */
@HiltViewModel
class MonitoringViewModel @Inject constructor(

    private val getPerformanceReportUseCase: GetPerformanceReportUseCase,

    private val monitoringStateMapper: MonitoringStateMapper

) : ViewModel() {





    private val _state =

        MutableStateFlow<MonitoringUiState>(

            MonitoringUiState.Loading

        )





    val state: StateFlow<MonitoringUiState>
        get() = _state.asStateFlow()







    fun onAction(

        action: MonitoringAction

    ) {


        when (action) {


            MonitoringAction.Refresh -> {


                refresh()


            }





            MonitoringAction.Reset -> {


                reset()


            }


        }


    }









    private fun refresh() {


        viewModelScope.launch {


            _state.value =

                MonitoringUiState.Loading





            try {



                val report =

                    getPerformanceReportUseCase()





                _state.value =

                    monitoringStateMapper.map(

                        report

                    )



            } catch (exception: Exception) {



                _state.value =

                    monitoringStateMapper.mapError(

                        exception

                    )


            }


        }


    }









    private fun reset() {


        _state.value =

            MonitoringUiState.Loading


    }





}
