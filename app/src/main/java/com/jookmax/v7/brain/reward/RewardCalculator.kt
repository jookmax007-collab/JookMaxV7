package com.jookmax.v7.brain.reward


import com.jookmax.v7.brain.trading.model.TradeExitReason
import com.jookmax.v7.brain.trading.model.TradeOutcome

import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class RewardCalculator @Inject constructor() {


    fun calculate(

        outcome: TradeOutcome

    ): RewardResult {


        val reward =

            if (outcome.positionSize <= 0.0) {

                0.0

            } else {

                outcome.profitLoss /

                outcome.positionSize

            }



        val normalizedReward =

            reward.coerceIn(-1.0, 1.0)



        val reason =

            when(outcome.exitReason) {


                TradeExitReason.TAKE_PROFIT ->

                    "Take profit reached"



                TradeExitReason.STOP_LOSS ->

                    "Stop loss reached"



                TradeExitReason.MANUAL ->

                    "Manual close"



                TradeExitReason.TIMEOUT ->

                    "Timeout close"



                TradeExitReason.UNKNOWN ->

                    "Unknown exit"

            }



        return RewardResult(

            reward = normalizedReward,

            success = outcome.success,

            reason = reason

        )

    }

}
