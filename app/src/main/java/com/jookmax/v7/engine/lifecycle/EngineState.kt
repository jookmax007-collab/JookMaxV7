package com.jookmax.v7.engine.lifecycle


sealed class EngineState {

    data object Idle : EngineState()

    data object Starting : EngineState()

    data object Running : EngineState()

    data object Paused : EngineState()

    data object Stopping : EngineState()

    data object Stopped : EngineState()

    data class Error(
        val message: String,
        val throwable: Throwable? = null
    ) : EngineState()
}