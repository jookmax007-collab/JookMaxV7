package com.jookmax.v7.engine.market.tick

import com.jookmax.v7.core.model.MarketTick
import com.jookmax.v7.core.model.Symbol
import org.junit.Assert.assertTrue
import org.junit.Test


class TickProcessorTest {


    @Test
    fun `valid market tick should be processed`() {


        val validator =
            TickValidator()


        val engine =
            TickEngine()


        val processor =
            TickProcessor(
                tickValidator = validator,
                tickEngine = engine
            )


        val tick =
            MarketTick(
                symbol = Symbol(code = "XAUUSD", description = "Gold vs USD"),
                price = 2500.0,
                timestamp = System.currentTimeMillis()
            )


        val candles =
            processor.process(tick)


        assertTrue(
            candles.isEmpty()
        )

    }

}
