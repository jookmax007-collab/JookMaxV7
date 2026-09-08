package com.jookmax.v7.brain.risk


import javax.inject.Inject
import javax.inject.Singleton



/**
 * Central Risk Calculation Engine.
 *
 * Connects:
 *
 * RiskProfile
 * PositionSizer
 * StopLossCalculator
 * TakeProfitCalculator
 *
 */
@Singleton
class RiskEngine @Inject constructor(


    private val positionSizer: PositionSizer,


    private val stopLossCalculator: StopLossCalculator,


    private val takeProfitCalculator: TakeProfitCalculator


) {



    fun calculateTradeRisk(

        profile: RiskProfile,

        entryPrice: Double,

        volatility: Double,

        isLong: Boolean

    ): RiskDecision {



        val stopLoss =

            stopLossCalculator.calculate(

                entryPrice = entryPrice,

                volatility = volatility,

                isLong = isLong

            )




        val takeProfit =

            takeProfitCalculator.calculate(

                entryPrice = entryPrice,

                stopLossPrice = stopLoss,

                isLong = isLong

            )




        val positionSize =

            positionSizer.calculate(

                profile = profile,

                stopLossDistance =
                    kotlin.math.abs(
                        entryPrice - stopLoss
                    )

            )




        return RiskDecision(

            positionSize = positionSize,

            stopLoss = stopLoss,

            takeProfit = takeProfit

        )

    }


}




data class RiskDecision(


    val positionSize: Double,


    val stopLoss: Double,


    val takeProfit: Double


)