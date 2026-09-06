package com.jookmax.v7.engine

import com.jookmax.v7.engine.lifecycle.EngineLifecycleManager
import com.jookmax.v7.engine.runtime.EngineCoroutineScope
import com.jookmax.v7.engine.runtime.EngineRuntimeTracker
import kotlinx.coroutines.flow.StateFlow


class JookMaxEngine(

    private val lifecycleManager: EngineLifecycleManager,
    private val runtimeTracker: EngineRuntimeTracker,
    private val coroutineScope: EngineCoroutineScope

) {


    val state: StateFlow<EngineState>
        get() = lifecycleManager.state



    fun start() {

        lifecycleManager.start()

        if (runtimeTracker.isRunning().not()) {

            runtimeTracker.start()

        }
    }



    fun pause() {

        lifecycleManager.pause()

    }



    fun resume() {

        lifecycleManager.resume()

    }



    fun stop() {

        lifecycleManager.stop()

        runtimeTracker.stop()

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

        coroutineScope.cancel()

    }
}