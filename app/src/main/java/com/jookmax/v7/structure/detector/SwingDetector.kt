package com.jookmax.v7.structure.detector


import com.jookmax.v7.core.model.MarketCandle
import com.jookmax.v7.structure.model.SwingPoint
import com.jookmax.v7.structure.model.SwingType


class SwingDetector {


    fun detect(
        candles: List<MarketCandle>
    ): List<SwingPoint> {


        if(candles.size < 3)
            return emptyList()


        val swings = mutableListOf<SwingPoint>()


        for(i in 1 until candles.size - 1){


            val previous = candles[i-1]

            val current = candles[i]

            val next = candles[i+1]


            if(
                current.high > previous.high &&
                current.high > next.high
            ){

                swings.add(
                    SwingPoint(
                        candle = current,
                        price = current.high,
                        type = SwingType.HIGH
                    )
                )
            }


            if(
                current.low < previous.low &&
                current.low < next.low
            ){

                swings.add(
                    SwingPoint(
                        candle = current,
                        price = current.low,
                        type = SwingType.LOW
                    )
                )
            }

        }


        return swings
    }

}
