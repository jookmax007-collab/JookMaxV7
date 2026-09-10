package com.jookmax.v7.brain.intelligence.memory


import com.jookmax.v7.brain.decision.DecisionResult
import com.jookmax.v7.brain.intelligence.validation.ValidatedDecision
import com.jookmax.v7.brain.context.MarketContext

import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class DecisionPatternFactory @Inject constructor() {



    fun create(

        marketContext: MarketContext,

        decisionResult: DecisionResult,

        validatedDecision: ValidatedDecision,

        reward: Double

    ): DecisionPattern {


        return DecisionPattern(

            symbol =
                marketContext.symbol,


            trend =
                marketContext.trend,


            rsi =
                marketContext.rsi,


            volatility =
                marketContext.volatility,


            action =
                validatedDecision.finalAction,


            confidence =
                validatedDecision.validationScore,


            approved =
                validatedDecision.approved,


            reward =
                reward

        )

    }

}