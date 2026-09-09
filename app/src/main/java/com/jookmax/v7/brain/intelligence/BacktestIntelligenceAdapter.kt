package com.jookmax.v7.brain.intelligence


import com.jookmax.v7.brain.backtest.BacktestResult
import com.jookmax.v7.brain.intelligence.memory.DecisionPattern
import com.jookmax.v7.domain.repository.PersistentDecisionMemoryRepository

import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class BacktestIntelligenceAdapter @Inject constructor(

    private val repository:
        PersistentDecisionMemoryRepository

) {


    suspend fun learnFromBacktest(

        result: BacktestResult

    ) {


        val patterns = result.trades.map { trade ->


            DecisionPattern(

                symbol = "XAUUSD",

                trend = "UNKNOWN",

                rsi = 0.0,

                volatility = 0.0,

                action = trade.action,

                confidence = result.winRate,

                approved = true,

                reward = trade.profitLoss

            )


        }


        repository.saveAll(

            patterns

        )


    }


}
