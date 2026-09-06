package com.jookmax.v7.engine.lifecycle

import com.jookmax.v7.engine.EngineState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class EngineLifecycleManager {


    private val _state =
        MutableStateFlow<EngineState>(EngineState.Idle)


    val state: StateFlow<EngineState>
        get() = _state.asStateFlow()



    fun start() {

        if (_state.value == EngineState.Running) {
            return
        }

        _state.value = EngineState.Starting


        try {

            _state.value = EngineState.Running

        } catch (e: Exception) {

            _state.value =
                EngineState.Error(
                    message = e.message ?: "Unknown error",
                    throwable = e
                )
        }
    }



    fun pause() {

        if (_state.value == EngineState.Running) {

            _state.value = EngineState.Paused

        }
    }



    fun resume() {

        if (_state.value == EngineState.Paused) {

            _state.value = EngineState.Running

        }
    }



    fun stop() {

        _state.value = EngineState.Stopping


        _state.value = EngineState.Stopped
    }


    fun reset(){

        _state.value = EngineState.Idle

    }
}