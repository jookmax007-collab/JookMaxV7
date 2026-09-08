package com.jookmax.v7.brain.risk


import javax.inject.Inject
import javax.inject.Singleton



/**
 * Dynamic Risk Adjustment Engine.
 *
 * Flow:
 *
 * Market Volatility
 *        |
 *        v
 * RiskMultiplier
 *        |
 *        v
 * Adjusted Risk Percent
 *
 */
@Singleton
class DynamicRiskManager @Inject constructor(

    private val riskMultiplier: RiskMultiplier

) {



    fun adjustRisk(

        profile: RiskProfile,

        volatility: Double

    ): RiskProfile {



        val multiplier =

            riskMultiplier.calculate(

                volatility

            )





        return profile.copy(

            riskPercent =

                profile.riskPercent * multiplier

        )

    }


}