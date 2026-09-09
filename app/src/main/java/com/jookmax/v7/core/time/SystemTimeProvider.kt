package com.jookmax.v7.core.time

/**
 * Default implementation of TimeProvider.
 *
 * Uses device system clock.
 */
class SystemTimeProvider : TimeProvider {

    override fun currentTimeMillis(): Long {
        return System.currentTimeMillis()
    }
}
