package com.jookmax.v7.brain.risk


import javax.inject.Inject
import javax.inject.Singleton



/**
 * Controls correlated exposure.
 */
@Singleton
class CorrelationRiskManager @Inject constructor() {



    fun isAllowed(

        activePositions: Int,

        maxPositions: Int = 5

    ): Boolean {



        return activePositions < maxPositions

    }


}