package com.jookmax.v7.core.event


import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton



/**
 * Dispatches engine events to registered subscribers.
 *
 * Connects EventBus stream to subscribers.
 */
@Singleton
class EventDispatcher @Inject constructor(

    private val eventBus: EventBus

) {


    private val subscribers =
        mutableListOf<EventSubscriber>()



    private var listeningJob: Job? = null





    fun register(
        subscriber: EventSubscriber
    ) {

        subscribers.add(subscriber)

    }





    fun unregister(
        subscriber: EventSubscriber
    ) {

        subscribers.remove(subscriber)

    }





    fun start(
        scope: CoroutineScope
    ) {


        if (listeningJob != null) {
            return
        }



        listeningJob =
            scope.launch {


                eventBus.events.collect { event ->


                    dispatch(event)


                }


            }


    }





    fun stop() {


        listeningJob?.cancel()

        listeningJob = null

    }





    private suspend fun dispatch(
        event: EngineEvent
    ) {


        subscribers.forEach { subscriber ->


            subscriber.onEvent(event)


        }


    }


}
