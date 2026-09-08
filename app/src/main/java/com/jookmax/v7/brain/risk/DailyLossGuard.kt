package com.jookmax.v7.brain.risk


import javax.inject.Inject
import javax.inject.Singleton



/**
 * Prevents excessive daily losses.
 */
@Singleton
class DailyLossGuard @Inject constructor() {



    fun isAllowed(

        dailyLoss: Double,

        accountBalance: Double,

        limitPercent: Double = 5.0

    ): Boolean {



        if (accountBalance <= 0)

            return false



        val lossPercent =

            (dailyLoss / accountBalance) * 100



        return lossPercent < limitPercent

    }


}