package com.jookmax.v7.presentation.monitoring


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.jookmax.v7.domain.monitoring.GetPerformanceReportUseCase
import com.jookmax.v7.domain.repository.MonitoringRepository

import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

import javax.inject.Inject



@HiltViewModel
class MonitoringViewModel @Inject constructor(


    private val getPerformanceReportUseCase: GetPerformanceReportUseCase,


    private val monitoringRepository: MonitoringRepository,


    private val monitoringStateMapper: MonitoringStateMapper


) : ViewModel() {





    private val _state =

        MutableStateFlow<MonitoringUiState>(

            MonitoringUiState.Loading

        )



    val state: StateFlow<MonitoringUiState>
        get() = _state.asStateFlow()





    init {

        observeMonitoring()

    }







    private fun observeMonitoring() {


        viewModelScope.launch {


            monitoringRepository
                .observeSnapshots()
                .collect {


                    refresh()


                }


        }


    }







    fun onAction(

        action: MonitoringAction

    ) {


        when(action) {


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


            try {


                val report =

                    getPerformanceReportUseCase()





                val snapshots =

                    monitoringRepository
                        .getSnapshots()





                _state.value =

                    monitoringStateMapper.map(

                        report = report,

                        snapshots = snapshots

                    )



            } catch(exception: Exception) {



                _state.value =

                    monitoringStateMapper.mapError(

                        exception

                    )


            }



        }


    }









    private fun reset() {


        monitoringRepository
            .clearHistory()



        _state.value =

            MonitoringUiState.Loading


    }



}