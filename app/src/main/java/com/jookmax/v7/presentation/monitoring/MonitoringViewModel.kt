package com.jookmax.v7.presentation.monitoring


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.jookmax.v7.domain.monitoring.GetPerformanceReportUseCase
import com.jookmax.v7.domain.monitoring.PerformanceReport

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
 * - Receiving monitoring reports
 * - Exposing monitoring state to UI
 *
 * Architecture:
 *
 * UI
 *  |
 *  v
 * MonitoringViewModel
 *  |
 *  v
 * GetPerformanceReportUseCase
 *  |
 *  v
 * Monitoring Domain
 */
@HiltViewModel
class MonitoringViewModel @Inject constructor(

    private val getPerformanceReportUseCase: GetPerformanceReportUseCase

) : ViewModel() {



    private val _report =
        MutableStateFlow<PerformanceReport?>(null)



    val report: StateFlow<PerformanceReport?>
        get() = _report.asStateFlow()






    fun refresh() {


        viewModelScope.launch {


            val result =

                getPerformanceReportUseCase()



            _report.value = result


        }


    }





}