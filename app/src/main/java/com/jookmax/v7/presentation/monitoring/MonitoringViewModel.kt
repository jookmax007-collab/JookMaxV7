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
 * - Exposing monitoring data to UI
 *
 * Architecture:
 *
 * UI
 *  |
 *  v
 * MonitoringViewModel
 *  |
 *  v
 * MonitoringUiState
 *  |
 *  v
 * GetPerformanceReportUseCase
 */
@HiltViewModel
class MonitoringViewModel @Inject constructor(

    private val getPerformanceReportUseCase: GetPerformanceReportUseCase

) : ViewModel() {





    private val _state =

        MutableStateFlow<MonitoringUiState>(

            MonitoringUiState.Loading

        )





    val state: StateFlow<MonitoringUiState>
        get() = _state.asStateFlow()







    fun refresh() {


        viewModelScope.launch {


            _state.value =

                MonitoringUiState.Loading





            try {



                val report =

                    getPerformanceReportUseCase()





                _state.value =

                    MonitoringUiState.Available(

                        report = report

                    )



            } catch (exception: Exception) {



                _state.value =

                    MonitoringUiState.Error(

                        message =
                            exception.message
                                ?: "Unknown monitoring error"

                    )



            }



        }


    }





}