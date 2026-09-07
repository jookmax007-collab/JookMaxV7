package com.jookmax.v7.core.events.subscriber


import com.jookmax.v7.core.events.EngineEvent
import com.jookmax.v7.core.events.EventSubscriber
import com.jookmax.v7.core.events.SystemEvent
import javax.inject.Inject


/**
 * Handles engine lifecycle and system events.
 *
 * Responsible for receiving:
 * - Engine Started
 * - Engine Stopped
 * - Engine Errors
 *
 * Future connections:
 * - Logger
 * - Monitoring
 * - HealthCheck
 * - Runtime analytics
 */
class EngineEventSubscriber @Inject constructor(

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


        // Future:
        // Logger.info("Engine started")
        // Monitoring update


    }





    private fun handleEngineStopped() {


        // Future:
        // Logger.info("Engine stopped")
        // Release monitoring state


    }





    private fun handleEngineError(
        event: SystemEvent.EngineError
    ) {


        val message =
            event.message


        val throwable =
            event.throwable


        // Future:
        // Logger.error(message, throwable)
        // HealthCheck update


    }


}