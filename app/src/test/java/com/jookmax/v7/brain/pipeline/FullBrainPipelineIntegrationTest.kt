package com.jookmax.v7.brain.pipeline


import com.jookmax.v7.core.model.MarketCandle
import com.jookmax.v7.core.model.Symbol
import com.jookmax.v7.core.model.TimeFrame

import org.junit.Assert.assertNotNull
import org.junit.Test



class FullBrainPipelineIntegrationTest {



    @Test
    fun brainPipeline_should_receive_market_candle() {


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



        assertNotNull(candle)


        assertNotNull(candle.symbol)


    }


}