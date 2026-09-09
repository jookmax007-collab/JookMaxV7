package com.jookmax.v7.core.event

/**
 * Represents system lifecycle events.
 */
sealed class SystemEvent(
    override val type: EventType = EventType.SYSTEM,
    override val metadata: EventMetadata = EventMetadata(
        source = "System",
        timestamp = System.currentTimeMillis()
    )
) : EngineEvent {


    data object EngineStarted : SystemEvent()


    data object EngineStopped : SystemEvent()


    data class EngineError(
        val message: String,
        val throwable: Throwable? = null
    ) : SystemEvent()
}
