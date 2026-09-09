package com.jookmax.v7.core.event

/**
 * Base event contract for JookMax engine events.
 *
 * Every engine event is also an application event.
 */
sealed interface EngineEvent : AppEvent
