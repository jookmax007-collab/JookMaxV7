package com.jookmax.v7.brain.risk


import javax.inject.Inject
import javax.inject.Singleton



/**
 * Final risk gate before trade decision.
 */
@Singleton
class TradeRiskValidator @Inject constructor(


    private val drawdownManager: DrawdownManager,


    private val dailyLossGuard: DailyLossGuard,


    private val correlationRiskManager: CorrelationRiskManager


) {



    fun validate(

        currentBalance: Double,

        peakBalance: Double,

        dailyLoss: Double,

        activePositions: Int

    ): Boolean {



        val drawdownAllowed =

            drawdownManager.isAllowed(

                currentBalance = currentBalance,

                peakBalance = peakBalance

            )





        val dailyLossAllowed =

            dailyLossGuard.isAllowed(

                dailyLoss = dailyLoss,

                accountBalance = currentBalance

            )





        val correlationAllowed =

            correlationRiskManager.isAllowed(

                activePositions = activePositions

            )





        return (

            drawdownAllowed &&

            dailyLossAllowed &&

            correlationAllowed

        )

    }


}
