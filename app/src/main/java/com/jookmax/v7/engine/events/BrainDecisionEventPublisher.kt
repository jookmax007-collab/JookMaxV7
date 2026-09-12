package com.jookmax.v7.engine.events


import com.jookmax.v7.brain.pipeline.BrainExecutionResult
import com.jookmax.v7.core.event.DecisionEvent
import com.jookmax.v7.core.event.EventBus

import javax.inject.Inject
import javax.inject.Singleton



/**
 * Publishes completed brain decisions into engine event stream.
 *
 * Flow:
 *
 * BrainPipeline
 *       |
 *       v
 * BrainExecutionResult
 *       |
 *       v
 * DecisionEvent.DecisionGenerated
 *       |
 *       v
 * EventBus
 */
@Singleton
class BrainDecisionEventPublisher @Inject constructor(

    private val eventBus: EventBus

) {



    fun publish(

        result: BrainExecutionResult,

        marketScore: Double,

        riskAllowed: Boolean,

        learningReward: Double

    ) {



        val event =

            DecisionEvent.DecisionGenerated(


                symbol =

                    result.context.marketContext.let {

                        com.jookmax.v7.core.model.Symbol(

                            code = it.symbol,

                            description = "Gold / USD"

                        )

                    },



                decision =

                    result.decision,



                intelligenceDecision =

                    result.intelligenceDecision,



                validatedDecision =

                    result.validatedDecision,



                marketScore =

                    marketScore,



                riskAllowed =

                    riskAllowed,



                learningReward =

                    learningReward


            )





        eventBus.publish(

            event

        )

    }


}
