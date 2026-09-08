package com.jookmax.v7.domain.monitoring


import com.jookmax.v7.monitoring.MetricsAnalytics

import javax.inject.Inject



/**
 * Provides performance monitoring report.
 *
 * Domain layer entry point for monitoring analytics.
 *
 * Includes:
 * - Runtime metrics
 * - Decision analytics
 */
class GetPerformanceReportUseCase @Inject constructor(

    private val metricsAnalytics: MetricsAnalytics

) {



    operator fun invoke():

            PerformanceReport {



        return PerformanceReport(



            totalProcessedEvents =

                metricsAnalytics.getTotalProcessedEvents(),





            totalFailedEvents =

                metricsAnalytics.getTotalFailedEvents(),





            averageLatencyMs =

                metricsAnalytics.getAverageLatency(),





            failureRate =

                metricsAnalytics.getFailureRate(),





            currentEngineState =

                metricsAnalytics.getLatestState(),





            totalDecisions =

                metricsAnalytics.getTotalDecisions(),





            buyDecisions =

                metricsAnalytics.getBuyDecisions(),





            sellDecisions =

                metricsAnalytics.getSellDecisions(),





            holdDecisions =

                metricsAnalytics.getHoldDecisions(),





            averageDecisionConfidence =

                metricsAnalytics.getAverageDecisionConfidence()



        )


    }



}