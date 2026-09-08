package com.jookmax.v7.engine.market.tick


import com.jookmax.v7.core.model.MarketCandle
import com.jookmax.v7.core.model.MarketTick
import com.jookmax.v7.core.model.TimeFrame



/**
 * Builds OHLC candles from incoming market ticks.
 */
class CandleBuilder(

    private val interval: CandleInterval

) {


    private var currentOpen: Double? = null

    private var currentHigh: Double? = null

    private var currentLow: Double? = null

    private var currentClose: Double? = null

    private var currentVolume: Double = 0.0

    private var candleStartTime: Long = 0L

    private var currentSymbol =
        null as com.jookmax.v7.core.model.Symbol?



    fun update(
        tick: MarketTick
    ): MarketCandle? {


        if (currentOpen == null) {

            startNewCandle(tick)

            return null

        }



        if (isExpired(tick.timestamp)) {


            val candle = buildCandle()



            startNewCandle(tick)



            return candle

        }



        updateValues(tick)



        return null

    }




    private fun startNewCandle(
        tick: MarketTick
    ) {

        currentSymbol = tick.symbol

        currentOpen = tick.price

        currentHigh = tick.price

        currentLow = tick.price

        currentClose = tick.price

        currentVolume = tick.volume ?: 0.0


        candleStartTime =
            alignTimestamp(
                tick.timestamp
            )

    }




    private fun updateValues(
        tick: MarketTick
    ) {


        currentHigh =
            maxOf(
                currentHigh ?: tick.price,
                tick.price
            )


        currentLow =
            minOf(
                currentLow ?: tick.price,
                tick.price
            )


        currentClose =
            tick.price


        currentVolume +=
            tick.volume ?: 0.0

    }





    private fun buildCandle(): MarketCandle {


        return MarketCandle(

            symbol = requireNotNull(currentSymbol),

            timeFrame =
                interval.toTimeFrame(),

            timestamp =
                candleStartTime,

            open =
                requireNotNull(currentOpen),

            high =
                requireNotNull(currentHigh),

            low =
                requireNotNull(currentLow),

            close =
                requireNotNull(currentClose),

            volume =
                currentVolume

        )

    }




    private fun isExpired(
        timestamp: Long
    ): Boolean {

        return timestamp >=
                candleStartTime + interval.millis

    }




    private fun alignTimestamp(
        timestamp: Long
    ): Long {

        return timestamp -
                (timestamp % interval.millis)

    }



}




private fun CandleInterval.toTimeFrame(): TimeFrame {

    return when(this) {

        CandleInterval.ONE_MINUTE ->
            TimeFrame.M1


        CandleInterval.FIVE_MINUTES ->
            TimeFrame.M5


        CandleInterval.FIFTEEN_MINUTES ->
            TimeFrame.M15


        CandleInterval.ONE_HOUR ->
            TimeFrame.H1

    }

}