package com.jookmax.v7.brain.learning


import com.jookmax.v7.brain.backtest.BacktestResult
import com.jookmax.v7.brain.decision.DecisionAction

import javax.inject.Inject
import javax.inject.Singleton



/**
 * Converts Backtest results into Learning Experiences.
 *
 * Backtest
 *    |
 *    v
 * LearningExperience
 *
 */
@Singleton
class BacktestLearningAdapter @Inject constructor(

    private val experienceManager: LearningExperienceManager

) {



    fun learnFromBacktest(

        result: BacktestResult

    ) {


        val reward =

            calculateReward(result)



        val success =

            result.netProfit > 0.0



        val confidence =

            result.winRate



        val experience =

            LearningExperience(

                decision = determineDominantAction(result),

                confidence = confidence,

                riskApproved = true,

                positionSize = 1.0,

                reward = reward,

                success = success

            )



        experienceManager.addExperience(

            experience

        )

    }





    private fun calculateReward(

        result: BacktestResult

    ): Double {


        return (

            result.winRate * 0.5 +

            normalizeProfit(result.netProfit) * 0.5

        ).coerceIn(

            0.0,

            1.0

        )

    }





    private fun normalizeProfit(

        profit: Double

    ): Double {


        return when {

            profit <= 0.0 -> 0.0

            profit >= 100.0 -> 1.0

            else -> profit / 100.0

        }

    }





    private fun determineDominantAction(

        result: BacktestResult

    ): DecisionAction {


        return when {


            result.buySignals > result.sellSignals ->

                DecisionAction.BUY



            result.sellSignals > result.buySignals ->

                DecisionAction.SELL



            else ->

                DecisionAction.HOLD

        }

    }

}
