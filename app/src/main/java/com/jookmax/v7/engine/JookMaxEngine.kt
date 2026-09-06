package com.jookmax.v7.engine


import com.jookmax.v7.brain.BrainManager
import com.jookmax.v7.core.events.EngineEvent
import com.jookmax.v7.core.events.EventBus
import com.jookmax.v7.engine.lifecycle.EngineLifecycleManager
import com.jookmax.v7.engine.runtime.EngineCoroutineScope
import com.jookmax.v7.engine.runtime.EngineRuntimeTracker

import kotlinx.coroutines.flow.StateFlow



class JookMaxEngine(

    private val lifecycleManager: EngineLifecycleManager,

    private val runtimeTracker: EngineRuntimeTracker,

    private val coroutineScope: EngineCoroutineScope,

    private val brainManager: BrainManager,

    private val eventBus: EventBus

) {



    val state: StateFlow<EngineState>
        get() = lifecycleManager.state





    fun start() {


        lifecycleManager.start()


        brainManager.initialize()


        if (runtimeTracker.isRunning().not()) {

            runtimeTracker.start()

        }

    }





    fun publishEvent(
        event: EngineEvent
    ) {


        eventBus.publish(event)


    }





    fun stop() {


        lifecycleManager.stop()


        runtimeTracker.stop()


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





    fun getRuntime(): Long {


        return runtimeTracker.getRuntimeMillis()


    }





    fun shutdown() {


        stop()


        brainManager.shutdown()


        coroutineScope.cancel()


    }


}