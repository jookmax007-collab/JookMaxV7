package com.jookmax.v7.engine.events.subscriber


import com.jookmax.v7.brain.analytics.DecisionAnalyticsRepository
import com.jookmax.v7.brain.analytics.DecisionRecord

import com.jookmax.v7.core.events.DecisionEvent
import com.jookmax.v7.core.events.EngineEvent
import com.jookmax.v7.core.events.EventSubscriber

import com.jookmax.v7.core.logging.Logger

import javax.inject.Inject
import javax.inject.Singleton



/**
 * Handles generated trading decisions.
 *
 * Flow:
 *
 * DecisionEngine
 *      ↓
 * DecisionEvent
 *      ↓
 * DecisionEventSubscriber
 *      ↓
 * DecisionAnalyticsRepository
 *      ↓
 * Room
 */
@Singleton
class DecisionEventSubscriber @Inject constructor(


    private val repository: DecisionAnalyticsRepository,


    private val logger: Logger


) : EventSubscriber {





    override suspend fun onEvent(

        event: EngineEvent

    ) {



        when (event) {


            is DecisionEvent.DecisionGenerated -> {


                handleDecision(

                    event

                )


            }


            else -> Unit


        }


    }









    private suspend fun handleDecision(

        event: DecisionEvent.DecisionGenerated

    ) {



        val decision = event.decision





        val record = DecisionRecord(


            symbol = event.symbol.code,


            action = decision.action.name,


            confidence = decision.confidence,


            marketScore = 0.0,


            riskAllowed = true,


            learningReward = 0.0,


            timestamp = event.timestamp


        )





        repository.saveDecision(

            record

        )





        logger.info(

            tag = "DecisionEventSubscriber",

            message =
                "Decision stored: ${event.symbol.code} ${decision.action}"

        )


    }



}