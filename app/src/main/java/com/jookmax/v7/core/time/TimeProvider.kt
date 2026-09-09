package com.jookmax.v7.core.time

/**
 * Provides current time for the application.
 *
 * Using an interface allows testing with fake time providers
 * instead of depending directly on system time.
 */
interface TimeProvider {

    /**
     * Returns current time in milliseconds.
     */
    fun currentTimeMillis(): Long
}
