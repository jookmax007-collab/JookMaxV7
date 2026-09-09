package com.jookmax.v7.monitoring


import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

import javax.inject.Inject
import javax.inject.Singleton



/**
 * Central monitoring component for JookMax engine.
 *
 * Responsible for:
 * - Health status tracking
 * - Performance snapshot management
 * - Runtime observation
 * - Performance history storage
 * - Decision metrics integration
 */
@Singleton
class EngineMonitor @Inject constructor(

    private val metricsHistory: MetricsHistory,

    private val decisionMetricsCollector: DecisionMetricsCollector

) {



    private val _health =
        MutableStateFlow<EngineHealth>(
            EngineHealth.Offline
        )


    val health: StateFlow<EngineHealth>
        get() = _health.asStateFlow()







    private val _latestSnapshot =
        MutableStateFlow<PerformanceSnapshot?>(null)


    val latestSnapshot: StateFlow<PerformanceSnapshot?>
        get() = _latestSnapshot.asStateFlow()







    fun updateHealth(

        health: EngineHealth

    ) {


        _health.value = health


    }









    fun updateSnapshot(

        snapshot: PerformanceSnapshot

    ) {


        _latestSnapshot.value = snapshot


        metricsHistory.addSnapshot(

            snapshot

        )


    }









    fun createSnapshot(

        engineState: String,

        processedEvents: Long = 0,

        failedEvents: Long = 0,

        activeBrains: Int = 0,

        processingLatencyMs: Long = 0

    ): PerformanceSnapshot {



        return PerformanceSnapshot(


            timestamp = System.currentTimeMillis(),


            engineState = engineState,


            processedEvents = processedEvents,


            failedEvents = failedEvents,


            activeBrains = activeBrains,


            processingLatencyMs = processingLatencyMs,


            totalDecisions =
                decisionMetricsCollector.getTotalDecisions(),


            buyDecisions =
                decisionMetricsCollector.getBuyDecisions(),


            sellDecisions =
                decisionMetricsCollector.getSellDecisions(),


            holdDecisions =
                decisionMetricsCollector.getHoldDecisions(),


            averageDecisionConfidence =
                decisionMetricsCollector.getAverageConfidence()



        )


    }









    fun getSnapshotHistory():

            List<PerformanceSnapshot> {


        return metricsHistory.getHistory()


    }









    fun reset() {


        _health.value =
            EngineHealth.Offline


        _latestSnapshot.value = null


        metricsHistory.clear()


        decisionMetricsCollector.reset()


    }



}
