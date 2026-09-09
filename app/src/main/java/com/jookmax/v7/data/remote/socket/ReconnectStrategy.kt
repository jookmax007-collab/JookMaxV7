package com.jookmax.v7.data.remote.socket

/**
 * Calculates the delay between WebSocket reconnect attempts.
 */
class ReconnectStrategy(
    private val initialDelayMillis: Long = 1_000L,
    private val maxDelayMillis: Long = 30_000L,
    private val multiplier: Double = 2.0
) {

    init {
        require(initialDelayMillis > 0) {
            "initialDelayMillis must be greater than zero"
        }

        require(maxDelayMillis >= initialDelayMillis) {
            "maxDelayMillis must be greater than or equal to initialDelayMillis"
        }

        require(multiplier >= 1.0) {
            "multiplier must be greater than or equal to 1.0"
        }
    }

    fun delayForAttempt(attempt: Int): Long {
        require(attempt >= 1) {
            "attempt must be greater than or equal to 1"
        }

        var delay = initialDelayMillis.toDouble()

        repeat(attempt - 1) {
            delay *= multiplier

            if (delay >= maxDelayMillis) {
                return maxDelayMillis
            }
        }

        return delay
            .coerceAtMost(maxDelayMillis.toDouble())
            .toLong()
    }

    fun reset(): Long = initialDelayMillis
}
