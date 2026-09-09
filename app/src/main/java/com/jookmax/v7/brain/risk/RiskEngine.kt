package com.jookmax.v7.brain.risk


import javax.inject.Inject
import javax.inject.Singleton



/**
 * Central Risk Calculation Engine.
 *
 * Pipeline:
 *
 * RiskProfile
 *      |
 *      v
 * DynamicRiskManager
 *      |
 *      v
 * PositionSizer
 *      |
 *      v
 * ExposureManager
 *      |
 *      v
 * StopLossCalculator
 *      |
 *      v
 * TakeProfitCalculator
 *      |
 *      v
 * RiskDecision
 *
 */
@Singleton
class RiskEngine @Inject constructor(


    private val positionSizer: PositionSizer,


    private val stopLossCalculator: StopLossCalculator,


    private val takeProfitCalculator: TakeProfitCalculator,


    private val exposureManager: ExposureManager,


    private val dynamicRiskManager: DynamicRiskManager


) {



    fun calculateTradeRisk(


        profile: RiskProfile,


        entryPrice: Double,


        volatility: Double,


        isLong: Boolean


    ): RiskDecision {



        val adjustedProfile =

            dynamicRiskManager.adjustRisk(

                profile = profile,

                volatility = volatility

            )





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





        val calculatedPositionSize =

            positionSizer.calculate(

                profile = adjustedProfile,

                stopLossDistance =

                    kotlin.math.abs(

                        entryPrice - stopLoss

                    )

            )





        val exposureAllowed =

            exposureManager.checkExposure(

                calculatedPositionSize

            )





        val finalPositionSize =

            if (exposureAllowed)

                calculatedPositionSize

            else

                0.0





        return RiskDecision(

            positionSize = finalPositionSize,

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
