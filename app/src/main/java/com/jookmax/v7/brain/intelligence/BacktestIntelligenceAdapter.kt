package com.jookmax.v7.brain.intelligence


import com.jookmax.v7.brain.backtest.BacktestResult
import javax.inject.Inject
import javax.inject.Singleton



/**
 * Converts backtest outcomes into intelligence memory.
 *
 * Backtest
 *    |
 *    v
 * DecisionExperience
 *    |
 *    v
 * DecisionMemory
 *
 */
@Singleton
class BacktestIntelligenceAdapter @Inject constructor(

    private val decisionMemory: DecisionMemory

) {


    fun learnFromBacktest(

        result: BacktestResult

    ) {


        result.trades.forEach { trade ->


            decisionMemory.add(

                DecisionExperience(

                    action = trade.action,

                    confidence = result.winRate,

                    reward = trade.profitLoss,

                    success = trade.success

                )

            )

        }

    }

}
