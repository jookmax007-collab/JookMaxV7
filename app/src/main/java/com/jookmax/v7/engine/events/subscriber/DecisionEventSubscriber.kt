package com.jookmax.v7.engine.events.subscriber


import com.jookmax.v7.core.events.DecisionEvent
import com.jookmax.v7.core.events.EngineEvent
import com.jookmax.v7.core.events.EventSubscriber

import com.jookmax.v7.core.logging.Logger

import com.jookmax.v7.domain.analytics.DecisionAnalyticsRepository

import javax.inject.Inject
import javax.inject.Singleton



/**
 * Handles generated trading decisions.
 *
 * Flow:
 *
 * DecisionEngine
 *        |
 *        v
 * DecisionEvent
 *        |
 *        v
 * DecisionEventSubscriber
 *        |
 *        v
 * DecisionAnalyticsRepository (Domain)
 *        |
 *        v
 * DecisionAnalyticsRepositoryImpl (Data)
 *        |
 *        v
 * Room Database
 *
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


        repository.saveDecision(

            event

        )





        logger.info(


            tag = "DecisionEventSubscriber",


            message =

                "Decision stored: ${event.symbol.code} ${event.decision.action}"


        )


    }



}