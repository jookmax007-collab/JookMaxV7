package com.jookmax.v7.engine.events.subscriber


import com.jookmax.v7.core.events.DecisionEvent
import com.jookmax.v7.core.events.EngineEvent
import com.jookmax.v7.core.events.EventSubscriber

import com.jookmax.v7.core.logging.Logger

import com.jookmax.v7.domain.analytics.DecisionAnalyticsRepository

import com.jookmax.v7.monitoring.DecisionMetricsCollector

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
 *        +----------------------+
 *        |                      |
 *        v                      v
 * DecisionAnalytics        DecisionMetrics
 * Repository               Collector
 *        |
 *        v
 * Room Database
 *
 */
@Singleton
class DecisionEventSubscriber @Inject constructor(


    private val repository: DecisionAnalyticsRepository,


    private val metricsCollector: DecisionMetricsCollector,


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





        metricsCollector.recordDecision(


            action = event.decision.action.name,


            confidence = event.decision.confidence


        )






        logger.info(


            tag = "DecisionEventSubscriber",


            message =

                "Decision stored and measured: ${event.symbol.code} ${event.decision.action}"


        )


    }



}