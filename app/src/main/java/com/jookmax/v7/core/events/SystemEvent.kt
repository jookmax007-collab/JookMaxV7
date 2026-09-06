package com.jookmax.v7.core.events

/**
 * Represents system lifecycle events.
 */
sealed class SystemEvent : EngineEvent {

    data object EngineStarted : SystemEvent()

    data object EngineStopped : SystemEvent()

    data class EngineError(
        val message: String,
        val throwable: Throwable? = null
    ) : SystemEvent()
}