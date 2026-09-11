package com.jookmax.v7.engine


import com.jookmax.v7.brain.BrainManager

import com.jookmax.v7.core.event.EventBus
import com.jookmax.v7.core.event.EventDispatcher
import com.jookmax.v7.core.event.SystemEvent

import com.jookmax.v7.engine.events.subscriber.DecisionEventSubscriber
import com.jookmax.v7.engine.events.subscriber.EngineEventSubscriber
import com.jookmax.v7.engine.events.subscriber.MarketEventSubscriber

import com.jookmax.v7.engine.lifecycle.EngineLifecycleManager
import com.jookmax.v7.engine.lifecycle.EngineState

import com.jookmax.v7.engine.market.MarketFeedManager

import com.jookmax.v7.engine.runtime.EngineCoroutineScope
import com.jookmax.v7.engine.runtime.EngineRuntimeTracker

import com.jookmax.v7.monitoring.EngineHealth
import com.jookmax.v7.monitoring.EngineMonitor
import com.jookmax.v7.monitoring.MetricsCollector
import com.jookmax.v7.monitoring.RuntimeObserver

import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

import kotlinx.coroutines.flow.StateFlow

import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class JookMaxEngine @Inject constructor(


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

    private val metricsCollector: MetricsCollector,

    private val marketFeedManager: MarketFeedManager


) {



    private var monitoringJob: Job? = null



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



        marketFeedManager.start(
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


        startMonitoringLoop()



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


        stopMonitoringLoop()


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


        stopMonitoringLoop()


    }





    private fun startMonitoringLoop() {


        if (monitoringJob != null) return



        monitoringJob =
            coroutineScope.scope.launch {


                while (true) {


                    delay(5000)


                    updatePerformanceSnapshot()


                }


            }


    }





    private fun stopMonitoringLoop() {


        monitoringJob?.cancel()


        monitoringJob = null


    }





    private fun updatePerformanceSnapshot() {


        val snapshot = engineMonitor.createSnapshot(


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
