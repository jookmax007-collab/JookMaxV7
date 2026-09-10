package com.jookmax.v7.brain.intelligence


import com.jookmax.v7.brain.decision.DecisionAction
import com.jookmax.v7.brain.intelligence.memory.DecisionPattern
import com.jookmax.v7.domain.repository.PersistentDecisionMemoryRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test



class IntelligenceMemoryIntegrationTest {


    @Test
    fun `memory retrieval should work with stored intelligence patterns`() =
        runBlocking {


            val repository =
                FakeIntelligenceMemoryRepository()



            repository.save(

                DecisionPattern(

                    symbol = "XAUUSD",

                    trend = "BULLISH",

                    rsi = 55.0,

                    volatility = 0.01,

                    action = DecisionAction.BUY,

                    confidence = 0.85,

                    approved = true,

                    reward = 1.0

                )

            )



            val analyzer =
                IntelligenceMemoryAnalyzer(

                    repository

                )



            val score =
                analyzer.calculateMemoryScore()



            assertEquals(

                true,

                score > 0.0

            )


            assertEquals(

                1,

                analyzer.getMemorySize()

            )

        }

}





class FakeIntelligenceMemoryRepository :

    PersistentDecisionMemoryRepository {



    private val items =
        mutableListOf<DecisionPattern>()



    override suspend fun save(
        pattern: DecisionPattern
    ) {

        items.add(pattern)

    }



    override suspend fun saveAll(
        patterns: List<DecisionPattern>
    ) {

        items.addAll(patterns)

    }



    override suspend fun getAll():

            List<DecisionPattern> {

        return items

    }



    override suspend fun getLatest():

            DecisionPattern? {

        return items.lastOrNull()

    }



    override suspend fun count():

            Int {

        return items.size

    }



    override suspend fun clear() {

        items.clear()

    }

}
