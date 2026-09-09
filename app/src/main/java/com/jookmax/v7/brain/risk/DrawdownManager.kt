package com.jookmax.v7.brain.risk


import javax.inject.Inject
import javax.inject.Singleton



/**
 * Controls maximum account drawdown.
 */
@Singleton
class DrawdownManager @Inject constructor() {



    fun isAllowed(

        currentBalance: Double,

        peakBalance: Double,

        maxDrawdownPercent: Double = 20.0

    ): Boolean {



        if (peakBalance <= 0)

            return false



        val drawdown =

            ((peakBalance - currentBalance) / peakBalance) * 100



        return drawdown < maxDrawdownPercent

    }


}
