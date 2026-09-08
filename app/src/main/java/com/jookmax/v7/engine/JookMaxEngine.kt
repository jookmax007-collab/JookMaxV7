package com.jookmax.v7.engine


import com.jookmax.v7.brain.BrainManager

import com.jookmax.v7.core.events.EventBus
import com.jookmax.v7.core.events.SystemEvent
import com.jookmax.v7.core.events.EventDispatcher

import com.jookmax.v7.engine.events.subscriber.EngineEventSubscriber
import com.jookmax.v7.engine.events.subscriber.MarketEventSubscriber
import com.jookmax.v7.engine.events.subscriber.DecisionEventSubscriber

import com.jookmax.v7.monitoring.EngineHealth
import com.jookmax.v7.monitoring.EngineMonitor
import com.jookmax.v7.monitoring.MetricsCollector
import com.jookmax.v7.monitoring.RuntimeObserver

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


    private val decisionEventSubscriber: DecisionEventSubscriber,


    private val lifecycleManager: EngineLifecycleManager,


    private val runtimeTracker: EngineRuntimeTracker,


    private val coroutineScope: EngineCoroutineScope,


    private val engineMonitor: EngineMonitor,


    private val runtimeObserver: RuntimeObserver,


    private val metricsCollector: MetricsCollector


) {



    val state: StateFlow<EngineState>
        get() = lifecycleManager.state







    fun start() {



        lifecycleManager.start()



        runtimeTracker.start()



        metricsCollector.recordEvent()






        eventDispatcher.register(

            marketEventSubscriber

        )





        eventDispatcher.register(

            engineEventSubscriber

        )





        eventDispatcher.register(

            decisionEventSubscriber

        )






        eventDispatcher.start(

            coroutineScope.scope

        )





        brainManager.initialize()





        engineMonitor.updateHealth(

            EngineHealth.Healthy

        )





        runtimeObserver.observe(

            "RUNNING"

        )





        updatePerformanceSnapshot()





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





        metricsCollector.recordEvent()





        eventDispatcher.stop()





        brainManager.shutdown()





        engineMonitor.updateHealth(

            EngineHealth.Offline

        )





        runtimeObserver.observe(

            "STOPPED"

        )





        updatePerformanceSnapshot()



    }









    fun pause() {



        lifecycleManager.pause()





        runtimeObserver.observe(

            "PAUSED"

        )





        metricsCollector.recordEvent()





        updatePerformanceSnapshot()



    }









    fun resume() {



        lifecycleManager.resume()





        runtimeObserver.observe(

            "RUNNING"

        )





        metricsCollector.recordEvent()





        updatePerformanceSnapshot()



    }









    fun reset() {



        lifecycleManager.reset()





        runtimeTracker.reset()





        engineMonitor.reset()





        runtimeObserver.reset()





        metricsCollector.reset()



    }









    fun shutdown() {



        stop()



        coroutineScope.cancel()



    }









    private fun updatePerformanceSnapshot() {



        val snapshot =

            engineMonitor.createSnapshot(



                engineState =

                    runtimeObserver.getCurrentState(),





                processedEvents =

                    metricsCollector.getProcessedEvents(),





                failedEvents =

                    metricsCollector.getFailedEvents(),





                processingLatencyMs =

                    metricsCollector.getAverageLatencyMs()



            )





        engineMonitor.updateSnapshot(

            snapshot

        )



    }



}