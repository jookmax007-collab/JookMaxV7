package com.jookmax.v7.engine.market.tick


/**
 * Candle generation interval.
 *
 * Defines how incoming ticks are grouped
 * into market candles.
 */
enum class CandleInterval(

    val millis: Long

) {


    ONE_MINUTE(
        60_000L
    ),


    FIVE_MINUTES(
        300_000L
    ),


    FIFTEEN_MINUTES(
        900_000L
    ),


    ONE_HOUR(
        3_600_000L
    )


}