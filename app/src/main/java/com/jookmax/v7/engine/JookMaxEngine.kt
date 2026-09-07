package com.jookmax.v7.engine


import com.jookmax.v7.brain.BrainManager

import com.jookmax.v7.core.events.EventBus
import com.jookmax.v7.core.events.SystemEvent
import com.jookmax.v7.core.events.EventDispatcher
import com.jookmax.v7.core.events.subscriber.EngineEventSubscriber
import com.jookmax.v7.core.events.subscriber.MarketEventSubscriber

import com.jookmax.v7.engine.lifecycle.EngineLifecycleManager
import com.jookmax.v7.engine.lifecycle.EngineState
import com.jookmax.v7.engine.runtime.EngineCoroutineScope
import com.jookmax.v7.engine.runtime.EngineRuntimeTracker

import kotlinx.coroutines.flow.StateFlow



class JookMaxEngine(

    private val brainManager: BrainManager,

    private val eventBus: EventBus,

    private val eventDispatcher: EventDispatcher,

    private val marketEventSubscriber: MarketEventSubscriber,

    private val engineEventSubscriber: EngineEventSubscriber,

    private val lifecycleManager: EngineLifecycleManager,

    private val runtimeTracker: EngineRuntimeTracker,

    private val coroutineScope: EngineCoroutineScope

) {



    val state: StateFlow<EngineState>
        get() = lifecycleManager.state





    fun start() {


        lifecycleManager.start()


        runtimeTracker.start()



        eventDispatcher.register(
            marketEventSubscriber
        )


        eventDispatcher.register(
            engineEventSubscriber
        )



        eventDispatcher.start(
            coroutineScope.scope
        )



        brainManager.initialize()



        eventBus.publish(
            SystemEvent.EngineStarted
        )


    }





    fun stop() {


        eventBus.publish(
            SystemEvent.EngineStopped
        )


        lifecycleManager.stop()


        runtimeTracker.stop()


        eventDispatcher.stop()


        brainManager.shutdown()


    }





    fun pause() {

        lifecycleManager.pause()

    }





    fun resume() {

        lifecycleManager.resume()

    }





    fun reset() {


        lifecycleManager.reset()


        runtimeTracker.reset()


    }





    fun shutdown() {


        stop()


        coroutineScope.cancel()


    }


}