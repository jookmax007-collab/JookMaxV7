package com.jookmax.v7.presentation.dashboard


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.jookmax.v7.engine.manager.EngineManager
import com.jookmax.v7.engine.lifecycle.EngineState
import com.jookmax.v7.monitoring.EngineMonitor

import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

import kotlinx.coroutines.launch

import javax.inject.Inject



@HiltViewModel
class DashboardViewModel @Inject constructor(


    private val engineManager: EngineManager,


    private val engineMonitor: EngineMonitor


) : ViewModel() {




    private val _state =
        MutableStateFlow(
            DashboardState()
        )



    val state: StateFlow<DashboardState> =
        _state.asStateFlow()






    init {


        observeEngine()


        observeMonitoring()


        observeMarket()


    }









    private fun observeMarket() {


        viewModelScope.launch {


            engineMonitor
                .marketPrice
                .collect { price ->



                    price ?: return@collect



                    _state.value =
                        _state.value.copy(


                            symbol =
                                price.symbol.code,


                            price =
                                price.price


                        )


                }


        }


    }









    private fun observeEngine() {


        viewModelScope.launch {


            engineManager
                .getEngine()
                .state
                .collect { engineState ->



                    _state.value =
                        _state.value.copy(

                            engineStatus =
                                mapEngineState(
                                    engineState
                                )

                        )


                }


        }


    }









    private fun observeMonitoring() {


        viewModelScope.launch {


            engineMonitor
                .latestSnapshot
                .collect { snapshot ->



                    snapshot ?: return@collect



                    _state.value =
                        _state.value.copy(


                            totalDecisions =
                                snapshot.totalDecisions,


                            buyDecisions =
                                snapshot.buyDecisions,


                            sellDecisions =
                                snapshot.sellDecisions,


                            holdDecisions =
                                snapshot.holdDecisions,


                            averageDecisionConfidence =
                                snapshot.averageDecisionConfidence,



                            brainDecision =
                                when {


                                    snapshot.buyDecisions >
                                            snapshot.sellDecisions &&
                                    snapshot.buyDecisions >
                                            snapshot.holdDecisions ->
                                        "BUY"



                                    snapshot.sellDecisions >
                                            snapshot.buyDecisions &&
                                    snapshot.sellDecisions >
                                            snapshot.holdDecisions ->
                                        "SELL"



                                    else ->
                                        "HOLD"


                                },


                            confidence =
                                (snapshot.averageDecisionConfidence * 100)
                                    .toInt()



                        )


                }


        }


    }









    private fun mapEngineState(

        state: EngineState

    ): String {


        return when(state) {


            EngineState.Idle ->
                "IDLE"


            EngineState.Starting ->
                "STARTING"


            EngineState.Running ->
                "ONLINE"


            EngineState.Paused ->
                "PAUSED"


            EngineState.Stopping ->
                "STOPPING"


            EngineState.Stopped ->
                "STOPPED"


            is EngineState.Error ->
                "ERROR"


        }


    }


}