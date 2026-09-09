package com.jookmax.v7.brain.risk


/**
 * Exposure constraints.
 *
 * Defines maximum allowed market exposure.
 */
data class ExposureLimit(

    val maxExposurePercent: Double = 5.0,

    val maxPositions: Int = 3

)
