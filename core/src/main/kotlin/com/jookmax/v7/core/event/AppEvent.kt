package com.jookmax.v7.core.event

/**
 * Base contract for all application events in JookMax V7.
 *
 * All system events must implement this interface.
 */
interface AppEvent {

    /**
     * Event category.
     */
    val type: EventType

    /**
     * Common event information.
     */
    val metadata: EventMetadata
}
