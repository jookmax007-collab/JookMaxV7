package com.jookmax.v7.brain.intelligence


import com.jookmax.v7.brain.decision.DecisionAction
import com.jookmax.v7.brain.intelligence.memory.CurrentMarketPattern
import com.jookmax.v7.brain.intelligence.memory.DecisionPattern
import com.jookmax.v7.brain.intelligence.memory.PatternMatcher
import com.jookmax.v7.domain.repository.PersistentDecisionMemoryRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test



class MemoryRetrievalEngineTest {



    @Test
    fun `retrieval should find similar historical decisions`() = runBlocking {


        val repository =
            FakeMemoryRepository()



        repository.save(

            DecisionPattern(

                symbol = "XAUUSD",

                trend = "BULLISH",

                rsi = 55.0,

                volatility = 0.30,

                action = DecisionAction.BUY,

                confidence = 0.85,

                approved = true,

                reward = 20.0

            )

        )



        val engine = MemoryRetrievalEngine(

            repository,

            PatternMatcher()

        )



        val current = CurrentMarketPattern(

            symbol = "XAUUSD",

            trend = "BULLISH",

            rsi = 56.0,

            volatility = 0.32,

            marketRegime = "TRENDING",

            session = "LONDON"

        )



        val result =

            engine.retrieve(current)



        assertEquals(

            1,

            result.size

        )



        assertTrue(

            result.first().action == DecisionAction.BUY

        )

    }

}





class FakeMemoryRepository :

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