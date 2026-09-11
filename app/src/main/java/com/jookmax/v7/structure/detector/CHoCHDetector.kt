package com.jookmax.v7.structure.detector


import com.jookmax.v7.core.model.MarketCandle
import com.jookmax.v7.structure.model.StructureEvent
import com.jookmax.v7.structure.model.SwingPoint
import com.jookmax.v7.structure.model.SwingType



class CHoCHDetector {


    fun detect(
        candles: List<MarketCandle>,
        swings: List<SwingPoint>
    ): Boolean {


        if(candles.isEmpty() || swings.size < 2)
            return false



        val latest =
            candles.last()



        val highs =
            swings.filter {

                it.type == SwingType.HIGH

            }



        val lows =
            swings.filter {

                it.type == SwingType.LOW

            }



        val previousHigh =
            highs
                .dropLast(1)
                .lastOrNull()



        val previousLow =
            lows
                .dropLast(1)
                .lastOrNull()



        val bearishChange =

            previousLow != null &&
            latest.close < previousLow.price



        val bullishChange =

            previousHigh != null &&
            latest.close > previousHigh.price



        return bearishChange || bullishChange

    }







    fun detectEvent(
        candles: List<MarketCandle>,
        swings: List<SwingPoint>
    ): StructureEvent {



        if(candles.isEmpty() || swings.size < 2)

            return StructureEvent.NONE





        val latest =

            candles.last()





        val highs =

            swings.filter {

                it.type == SwingType.HIGH

            }





        val lows =

            swings.filter {

                it.type == SwingType.LOW

            }





        val previousHigh =

            highs
                .dropLast(1)
                .lastOrNull()





        val previousLow =

            lows
                .dropLast(1)
                .lastOrNull()





        if(
            previousHigh != null &&
            latest.close > previousHigh.price
        ) {

            return StructureEvent.BULLISH_CHOCH

        }





        if(
            previousLow != null &&
            latest.close < previousLow.price
        ) {

            return StructureEvent.BEARISH_CHOCH

        }





        return StructureEvent.NONE

    }


}