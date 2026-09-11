package com.jookmax.v7.structure.detector


import com.jookmax.v7.core.model.MarketCandle
import com.jookmax.v7.structure.model.SwingPoint
import com.jookmax.v7.structure.model.SwingType
import com.jookmax.v7.structure.model.StructureEvent



class BOSDetector {


    fun detect(
        candles: List<MarketCandle>,
        swings: List<SwingPoint>
    ): Boolean {


        if(candles.isEmpty() || swings.isEmpty())
            return false


        val latestCandle = candles.last()


        val lastHigh =
            swings
                .filter {
                    it.type == SwingType.HIGH
                }
                .lastOrNull()


        val lastLow =
            swings
                .filter {
                    it.type == SwingType.LOW
                }
                .lastOrNull()



        val bullishBreak =
            lastHigh != null &&
            latestCandle.close > lastHigh.price



        val bearishBreak =
            lastLow != null &&
            latestCandle.close < lastLow.price



        return bullishBreak || bearishBreak
    }





    fun detectEvent(
        candles: List<MarketCandle>,
        swings: List<SwingPoint>
    ): StructureEvent {


        if(candles.isEmpty() || swings.isEmpty())
            return StructureEvent.NONE



        val latestCandle = candles.last()



        val lastHigh =
            swings
                .filter {
                    it.type == SwingType.HIGH
                }
                .lastOrNull()



        val lastLow =
            swings
                .filter {
                    it.type == SwingType.LOW
                }
                .lastOrNull()



        if(
            lastHigh != null &&
            latestCandle.close > lastHigh.price
        ) {

            return StructureEvent.BULLISH_BOS

        }



        if(
            lastLow != null &&
            latestCandle.close < lastLow.price
        ) {

            return StructureEvent.BEARISH_BOS

        }



        return StructureEvent.NONE

    }


}