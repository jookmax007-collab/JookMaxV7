package com.jookmax.v7.brain.intelligence


import com.jookmax.v7.brain.backtest.BacktestResult
import com.jookmax.v7.brain.intelligence.memory.DecisionPattern
import com.jookmax.v7.domain.repository.PersistentDecisionMemoryRepository

import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class BacktestIntelligenceAdapter @Inject constructor(


    private val repository: PersistentDecisionMemoryRepository


) {



    suspend fun learnFromBacktest(


        result: BacktestResult


    ) {



        val patterns = result.trades.mapNotNull { trade ->



            val context =

                trade.learningContext

                    ?: return@mapNotNull null






            DecisionPattern(



                /**
                 * Real market snapshot
                 */
                symbol = context.symbol,



                trend = context.trend,



                rsi = context.rsi,



                volatility = context.volatility,



                /**
                 * Brain action
                 */
                action = trade.action,



                /**
                 * Confidence at decision time
                 */
                confidence = context.confidence,



                /**
                 * Decision passed validation
                 */
                approved = true,



                /**
                 * Real trade result
                 */
                reward = trade.profitLoss,



                timestamp = trade.closedAt
                    ?: System.currentTimeMillis()

            )

        }






        if (patterns.isNotEmpty()) {


            repository.saveAll(

                patterns

            )


        }


    }


}