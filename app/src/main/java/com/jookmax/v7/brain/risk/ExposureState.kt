package com.jookmax.v7.brain.risk


/**
 * Current market exposure state.
 *
 * Tracks active risk usage.
 */
data class ExposureState(

    val usedExposure: Double = 0.0,

    val maxExposure: Double = 5.0,

    val activePositions: Int = 0

) {


    fun isAllowed(): Boolean {

        return usedExposure < maxExposure

    }


}
