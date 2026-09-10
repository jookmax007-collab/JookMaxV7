package com.jookmax.v7.brain.intelligence


import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry

import androidx.room.Room

import com.jookmax.v7.data.local.dao.DecisionMemoryDao
import com.jookmax.v7.data.local.database.JookMaxDatabase
import com.jookmax.v7.data.mapper.DecisionMemoryMapper

import com.jookmax.v7.brain.intelligence.memory.DecisionPattern
import com.jookmax.v7.brain.decision.DecisionAction

import kotlinx.coroutines.runBlocking

import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

import org.junit.runner.RunWith



@RunWith(AndroidJUnit4::class)
class DecisionMemoryIntegrationTest {



    private lateinit var database: JookMaxDatabase

    private lateinit var dao: DecisionMemoryDao



    @Before
    fun setup() {


        val context =
            InstrumentationRegistry
                .getInstrumentation()
                .targetContext


        database =
            Room.inMemoryDatabaseBuilder(
                context,
                JookMaxDatabase::class.java
            )
                .allowMainThreadQueries()
                .build()


        dao =
            database.decisionMemoryDao()

    }



    @After
    fun close() {

        database.close()

    }



    @Test
    fun saveDecisionMemory_shouldPersistSuccessfully() =
        runBlocking {


            val pattern =
                DecisionPattern(

                    symbol = "XAUUSD",

                    trend = "BULLISH",

                    rsi = 60.0,

                    volatility = 0.25,

                    action = DecisionAction.BUY,

                    confidence = 0.85,

                    approved = true,

                    reward = 10.0

                )



            dao.insert(
                DecisionMemoryMapper.toEntity(pattern)
            )



            val count =
                dao.count()



            val latest =
                dao.getLatest()



            assertEquals(
                1,
                count
            )


            assertNotNull(
                latest
            )


        }

}