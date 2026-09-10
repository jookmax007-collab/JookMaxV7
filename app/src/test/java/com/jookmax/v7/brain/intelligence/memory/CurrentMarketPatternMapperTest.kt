package com.jookmax.v7.brain.intelligence.memory


import com.jookmax.v7.brain.context.MarketContext
import org.junit.Assert.assertEquals
import org.junit.Test



class CurrentMarketPatternMapperTest {



    @Test
    fun `market context should map to current market pattern`() {



        val context = MarketContext(

            symbol = "XAUUSD",

            price = 2500.0,

            trend = "BULLISH",

            rsi = 55.0,

            volatility = 0.30,

            marketRegime = "TREND_UP",

            session = "LONDON",

            dxy = 0.0,

            yield = 0.0,

            newsRisk = 0.0

        )



        val mapper =

            CurrentMarketPatternMapper()



        val result =

            mapper.map(context)



        assertEquals(

            "XAUUSD",

            result.symbol

        )


        assertEquals(

            "BULLISH",

            result.trend

        )


        assertEquals(

            55.0,

            result.rsi,

            0.0

        )


        assertEquals(

            "TREND_UP",

            result.marketRegime

        )


        assertEquals(

            "LONDON",

            result.session

        )

    }

}