package com.jookmax.v7.brain.pipeline


import androidx.test.ext.junit.runners.AndroidJUnit4

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest

import javax.inject.Inject

import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

import org.junit.runner.RunWith

import com.jookmax.v7.core.model.MarketCandle
import com.jookmax.v7.core.model.Symbol
import com.jookmax.v7.core.model.TimeFrame



@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class BrainPipelineRuntimeTest {


    @get:Rule
    val hiltRule = HiltAndroidRule(this)



    @Inject
    lateinit var brainPipeline: BrainPipeline




    @Test
    fun brainPipeline_should_execute_full_brain_cycle() {


        hiltRule.inject()



        val candle = MarketCandle(

            symbol = Symbol(

                code = "XAUUSD",

                description = "Gold / USD"

            ),


            timeFrame = TimeFrame.M15,


            timestamp = System.currentTimeMillis(),


            open = 2500.0,


            high = 2520.0,


            low = 2490.0,


            close = 2515.0,


            volume = 1000.0

        )




        val result = kotlinx.coroutines.runBlocking {

            brainPipeline.execute(candle)

        }




        assertNotNull(result)


        assertNotNull(
            result.decision
        )


        assertNotNull(
            result.intelligenceDecision
        )


        assertNotNull(
            result.validatedDecision
        )




        println(
            """
            
            ==============================
            JookMax V7 Brain Runtime
            ==============================

            Decision:
            ${result.decision}

            Intelligence:
            ${result.intelligenceDecision}

            Validation:
            ${result.validatedDecision}

            ==============================

            """.trimIndent()
        )



        assertTrue(

            result.validatedDecision.validationScore >= 0.0

        )


    }


}
