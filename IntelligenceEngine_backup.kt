package com.jookmax.v7.brain.intelligence


import com.jookmax.v7.brain.context.MarketContext
import com.jookmax.v7.brain.decision.DecisionAction
import com.jookmax.v7.brain.decision.DecisionResult
import com.jookmax.v7.brain.intelligence.memory.DecisionPatternFactory
import com.jookmax.v7.brain.intelligence.validation.ValidatedDecision
import com.jookmax.v7.domain.repository.PersistentDecisionMemoryRepository

import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class IntelligenceEngine @Inject constructor(


    private val advisor: IntelligenceAdvisor,


    private val memoryAnalyzer: IntelligenceMemoryAnalyzer,


    private val persistentDecisionMemoryRepository:
    PersistentDecisionMemoryRepository,


    private val decisionPatternFactory:
    DecisionPatternFactory


) {



    suspend fun generateDecision(

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



        return IntelligenceDecision(

            action = decisionResult.action,

            confidence = confidence,

            reason = reason

        )

    }






    suspend fun saveExperience(

        decisionResult: DecisionResult,


        marketContext: MarketContext,


        validatedDecision: ValidatedDecision,


        reward: Double

    ) {


        val pattern =

            decisionPatternFactory.create(

                marketContext = marketContext,

                decisionResult = decisionResult,

                validatedDecision = validatedDecision,

                reward = reward

            )



        persistentDecisionMemoryRepository.save(

            pattern

        )

    }


}