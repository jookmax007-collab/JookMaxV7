package com.jookmax.v7.engine.events.subscriber


import com.jookmax.v7.core.event.DecisionEvent
import com.jookmax.v7.core.event.EngineEvent
import com.jookmax.v7.core.event.EventSubscriber

import com.jookmax.v7.core.logging.Logger

import com.jookmax.v7.domain.analytics.DecisionAnalyticsRepository

import com.jookmax.v7.monitoring.DecisionMetricsCollector
import com.jookmax.v7.monitoring.EngineMonitor
import com.jookmax.v7.monitoring.LatestBrainDecision

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
 *
 *        |
 *        v
 *
 * EngineMonitor
 *        |
 *        v
 * Dashboard UI
 *
 */
@Singleton
class DecisionEventSubscriber @Inject constructor(


    private val repository: DecisionAnalyticsRepository,


    private val metricsCollector: DecisionMetricsCollector,


    private val engineMonitor: EngineMonitor,


    private val logger: Logger


) : EventSubscriber {





    override suspend fun onEvent(

        event: EngineEvent

    ) {


        when(event) {


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



        /*
         *
         * Store complete decision event
         *
         * Contains:
         *
         * DecisionResult
         * IntelligenceDecision
         * ValidatedDecision
         *
         */


        repository.saveDecision(

            event

        )









        /*
         *
         * Raw Decision Metrics
         *
         */


        metricsCollector.recordDecision(


            action = event.decision.action.name,


            confidence = event.decision.confidence


        )









        /*
         *
         * Validation Intelligence Metrics
         *
         */


        metricsCollector.recordValidation(


            approved = event.validatedDecision.approved,


            validationScore = event.validatedDecision.validationScore


        )









        /*
         *
         * Publish latest AI Brain Decision
         *
         * Monitoring -> Dashboard
         *
         */


        engineMonitor.updateLatestDecision(


            LatestBrainDecision(


                symbol =

                    event.symbol.code,



                action =

                    event.decision.action.name,



                confidence =

                    event.decision.confidence,



                approved =

                    event.validatedDecision.approved,



                validationScore =

                    event.validatedDecision.validationScore,



                marketScore =

                    event.marketScore,



                riskAllowed =

                    event.riskAllowed,



                learningReward =

                    event.learningReward



            )


        )









        logger.info(


            tag = "DecisionEventSubscriber",


            message =

                "Decision stored: ${event.symbol.code} " +

                "Action=${event.decision.action} " +

                "Validated=${event.validatedDecision.approved}"


        )


    }





}