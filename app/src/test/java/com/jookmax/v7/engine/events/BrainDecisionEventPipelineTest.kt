package com.jookmax.v7.engine.events


import com.jookmax.v7.brain.decision.DecisionAction
import com.jookmax.v7.brain.decision.DecisionResult
import com.jookmax.v7.brain.intelligence.IntelligenceDecision
import com.jookmax.v7.brain.intelligence.validation.ValidatedDecision
import com.jookmax.v7.core.event.DecisionEvent
import com.jookmax.v7.core.logging.Logger
import com.jookmax.v7.domain.analytics.DecisionAnalyticsRepository
import com.jookmax.v7.engine.events.subscriber.DecisionEventSubscriber
import com.jookmax.v7.monitoring.DecisionMetricsCollector
import com.jookmax.v7.monitoring.EngineMonitor
import com.jookmax.v7.monitoring.LatestBrainDecision
import com.jookmax.v7.monitoring.MetricsHistory
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import kotlinx.coroutines.runBlocking


class BrainDecisionEventPipelineTest {


    @Test
    fun `decision event should reach monitoring pipeline`() =
        runBlocking {


            val repository =
                FakeDecisionAnalyticsRepository()


            val metrics =
                DecisionMetricsCollector()


            val monitor =
                EngineMonitor(
                    MetricsHistory(),
                    metrics
                )


            val subscriber =
                DecisionEventSubscriber(
                    repository,
                    metrics,
                    monitor,
                    FakeLogger()
                )



            val intelligenceDecision =
                IntelligenceDecision(
                    action = DecisionAction.BUY,
                    confidence = 0.90,
                    reason = "test"
                )



            val decision =
                DecisionResult(
                    action = DecisionAction.BUY,
                    confidence = 0.85
                )



            val validated =
                ValidatedDecision(
                    originalDecision = intelligenceDecision,
                    approved = true,
                    finalAction = DecisionAction.BUY,
                    validationScore = 0.95,
                    validationReasons = listOf("test")
                )



            val event =
                DecisionEvent.DecisionGenerated(

                    symbol = com.jookmax.v7.core.model.Symbol(
                        code = "XAUUSD",
                        description = "Gold / USD"
                    ),

                    decision = decision,

                    intelligenceDecision = intelligenceDecision,

                    validatedDecision = validated,

                    marketScore = 0.8,

                    riskAllowed = true,

                    learningReward = 10.0

                )



            subscriber.onEvent(event)



            assertEquals(
                1,
                repository.items.size
            )


            assertEquals(
                1,
                metrics.getTotalDecisions()
            )


            assertNotNull(
                monitor.latestDecision.value
            )


            assertEquals(
                "BUY",
                monitor.latestDecision.value!!.action
            )

        }

}



class FakeDecisionAnalyticsRepository :
    DecisionAnalyticsRepository {


    val items =
        mutableListOf<DecisionEvent.DecisionGenerated>()



    override suspend fun saveDecision(
        event: DecisionEvent.DecisionGenerated
    ) {

        items.add(event)

    }



    override suspend fun getDecisions():
            List<DecisionEvent.DecisionGenerated> {

        return items

    }



    override suspend fun clearDecisions() {

        items.clear()

    }

}




class FakeLogger : Logger {


    override fun debug(
        tag: String,
        message: String
    ) {}



    override fun info(
        tag: String,
        message: String
    ) {}



    override fun warning(
        tag: String,
        message: String
    ) {}



    override fun error(
        tag: String,
        message: String,
        throwable: Throwable?
    ) {}

}
