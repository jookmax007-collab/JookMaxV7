package com.jookmax.v7.core.events.subscriber


import com.jookmax.v7.core.events.EngineEvent
import com.jookmax.v7.core.events.EventSubscriber
import com.jookmax.v7.core.events.SystemEvent
import com.jookmax.v7.core.logging.Logger

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

    private val logger: Logger

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


    }









    private fun handleEngineStopped() {


        logger.info(

            tag = "EngineEventSubscriber",

            message = "Engine stopped event received"

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


    }



}