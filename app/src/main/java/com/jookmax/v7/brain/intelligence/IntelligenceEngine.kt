package com.jookmax.v7.brain.intelligence


import com.jookmax.v7.brain.decision.DecisionAction
import com.jookmax.v7.brain.decision.DecisionResult
import com.jookmax.v7.brain.intelligence.memory.DecisionPattern
import com.jookmax.v7.domain.repository.PersistentDecisionMemoryRepository

import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class IntelligenceEngine @Inject constructor(


    private val advisor: IntelligenceAdvisor,


    private val memoryAnalyzer: IntelligenceMemoryAnalyzer,


    private val persistentDecisionMemoryRepository:
        PersistentDecisionMemoryRepository


) {



    suspend fun evaluate(

        decisionResult: DecisionResult

    ): IntelligenceDecision {



        val advisorAdjustment =

            advisor.calculateAdjustment()



        val memoryAdjustment =

            memoryAnalyzer.calculateAdjustment()



        val finalAdjustment =

            (

                    advisorAdjustment *

                    memoryAdjustment

                    )

                .coerceIn(

                    0.8,

                    1.2

                )



        val confidence =

            (

                    decisionResult.confidence *

                    finalAdjustment

                    )

                .coerceIn(

                    0.0,

                    1.0

                )



        val reason =

            when(decisionResult.action) {


                DecisionAction.BUY ->

                    "Bullish intelligence alignment"



                DecisionAction.SELL ->

                    "Bearish intelligence alignment"



                DecisionAction.HOLD ->

                    "Insufficient intelligence confidence"

            }



        val intelligenceDecision =

            IntelligenceDecision(

                action = decisionResult.action,

                confidence = confidence,

                reason = reason

            )



        persistentDecisionMemoryRepository.save(

            DecisionPattern(

                symbol = "XAUUSD",

                trend = "UNKNOWN",

                rsi = 0.0,

                volatility = 0.0,

                action = decisionResult.action,

                confidence = confidence,

                approved = true,

                reward = 0.0

            )

        )



        return intelligenceDecision

    }


}