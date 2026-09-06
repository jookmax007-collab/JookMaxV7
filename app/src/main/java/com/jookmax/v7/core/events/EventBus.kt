package com.jookmax.v7.core.events


import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow



/**
 * Central event dispatcher for JookMax engine events.
 *
 * Used for communication between:
 * Market Data
 * Engine
 * Brain systems
 * Monitoring layers
 */
class EventBus {



    private val _events =
        MutableSharedFlow<EngineEvent>(
            extraBufferCapacity = 64
        )



    val events: SharedFlow<EngineEvent>
        get() = _events.asSharedFlow()





    fun publish(
        event: EngineEvent
    ) {

        _events.tryEmit(event)

    }



}