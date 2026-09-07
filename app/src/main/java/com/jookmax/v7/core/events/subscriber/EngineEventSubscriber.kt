package com.jookmax.v7.core.events.subscriber


import com.jookmax.v7.core.events.EngineEvent
import com.jookmax.v7.core.events.EventSubscriber
import com.jookmax.v7.core.events.SystemEvent

import com.jookmax.v7.core.logging.Logger

import com.jookmax.v7.monitoring.EngineHealth
import com.jookmax.v7.monitoring.EngineMonitor

import javax.inject.Inject
import javax.inject.Singleton



/**
 * Handles engine lifecycle and system events.
 *
 * Responsible for receiving:
 * - Engine Started
 * - Engine Stopped
 * - Engine Errors
 *
 * Connected with:
 * - Central Logger
 * - Monitoring
 * - HealthCheck
 */
@Singleton
class EngineEventSubscriber @Inject constructor(


    private val logger: Logger,


    private val engineMonitor: EngineMonitor


) : EventSubscriber {





    override suspend fun onEvent(

        event: EngineEvent

    ) {



        when (event) {



            is SystemEvent.EngineStarted -> {


                handleEngineStarted()


            }






            is SystemEvent.EngineStopped -> {


                handleEngineStopped()


            }






            is SystemEvent.EngineError -> {


                handleEngineError(

                    event

                )


            }






            else -> Unit


        }



    }









    private fun handleEngineStarted() {



        logger.info(

            tag = "EngineEventSubscriber",

            message = "Engine started event received"

        )




        engineMonitor.updateHealth(

            EngineHealth.Healthy

        )



    }









    private fun handleEngineStopped() {



        logger.info(

            tag = "EngineEventSubscriber",

            message = "Engine stopped event received"

        )




        engineMonitor.updateHealth(

            EngineHealth.Offline

        )



    }









    private fun handleEngineError(

        event: SystemEvent.EngineError

    ) {



        logger.error(

            tag = "EngineEventSubscriber",

            message = event.message,

            throwable = event.throwable

        )





        engineMonitor.updateHealth(

            EngineHealth.Error(

                message = event.message,

                throwable = event.throwable

            )

        )



    }



}
