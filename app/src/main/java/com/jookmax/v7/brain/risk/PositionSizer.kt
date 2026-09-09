package com.jookmax.v7.brain.risk


import javax.inject.Inject
import javax.inject.Singleton



/**
 * Calculates trading position size
 * based on risk profile.
 *
 * Formula:
 *
 * Risk Amount =
 * Account Balance * Risk %
 *
 * Position Size =
 * Risk Amount / Stop Loss Distance
 *
 */
@Singleton
class PositionSizer @Inject constructor() {



    fun calculate(

        profile: RiskProfile,

        stopLossDistance: Double

    ): Double {



        if (stopLossDistance <= 0) {

            return 0.0

        }



        val riskAmount =

            profile.accountBalance *
                    (profile.riskPercent / 100.0)



        return riskAmount / stopLossDistance

    }


}
