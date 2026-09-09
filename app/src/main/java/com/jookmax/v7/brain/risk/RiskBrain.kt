package com.jookmax.v7.brain.risk


import javax.inject.Inject
import javax.inject.Singleton



/**
 * Risk Intelligence Brain
 *
 * Pipeline:
 *
 * Market Analysis
 *        |
 *        v
 * RiskBrain
 *        |
 *        v
 * RiskEngine
 *        |
 *        v
 * RiskDecision
 */
@Singleton
class RiskBrain @Inject constructor(

    private val riskEngine: RiskEngine

) {



    fun evaluate(

        profile: RiskProfile,

        entryPrice: Double,

        volatility: Double,

        isLong: Boolean

    ): RiskResult {



        val decision =

            riskEngine.calculateTradeRisk(

                profile = profile,

                entryPrice = entryPrice,

                volatility = volatility,

                isLong = isLong

            )





        val allowed =

            decision.positionSize > 0.0





        return RiskResult(

            decision = decision,

            allowed = allowed

        )

    }





    fun reset() {

        // Future state reset hook

    }


}





data class RiskResult(

    val decision: RiskDecision,

    val allowed: Boolean

)
