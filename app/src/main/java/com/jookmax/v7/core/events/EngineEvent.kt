package com.jookmax.v7.core.events

/**
 * Base event contract for JookMax engine events.
 *
 * Every engine event is also an application event.
 */
sealed interface EngineEvent : AppEvent