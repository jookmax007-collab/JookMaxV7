package com.jookmax.v7.core.event


/**
 * Contract for objects that receive engine events.
 *
 * Subscribers listen to events dispatched by EventDispatcher
 * and react based on event type.
 */
interface EventSubscriber {


    /**
     * Handles incoming engine event.
     */
    suspend fun onEvent(
        event: EngineEvent
    )

}
