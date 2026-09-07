package com.jookmax.v7.core.events

/**
 * Base contract for all application events in JookMax V7.
 *
 * All events inside the system must implement this interface.
 *
 * Examples:
 * - MarketEvent
 * - EngineEvent
 * - SystemEvent
 */
interface AppEvent {

    /**
     * Defines the category of this event.
     */
    val type: EventType

    /**
     * Contains common event information.
     */
    val metadata: EventMetadata
}