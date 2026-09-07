package com.jookmax.v7.core.events

/**
 * Common information attached to every application event.
 */
data class EventMetadata(

    val source: String,

    val timestamp: Long,

    val priority: EventPriority = EventPriority.NORMAL

)