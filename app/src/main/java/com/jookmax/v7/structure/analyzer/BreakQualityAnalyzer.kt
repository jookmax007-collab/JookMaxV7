package com.jookmax.v7.structure.analyzer


import com.jookmax.v7.core.model.MarketCandle
import com.jookmax.v7.structure.model.BreakQuality
import com.jookmax.v7.structure.model.StructureEvent
import com.jookmax.v7.structure.model.SwingPoint

import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class BreakQualityAnalyzer @Inject constructor() {



    fun analyze(

        candles: List<MarketCandle>,

        swing: SwingPoint?,

        event: StructureEvent

    ): BreakQuality {



        val lastCandle = candles.lastOrNull()



        if (

            lastCandle == null ||

            swing == null ||

            event == StructureEvent.NONE

        ) {

            return BreakQuality(

                score = 0.0,

                confidence = 0.0,

                valid = false

            )

        }



        val range =

            lastCandle.high - lastCandle.low



        if (range <= 0) {

            return BreakQuality(

                score = 0.0,

                confidence = 0.0,

                valid = false

            )

        }



        val body =

            kotlin.math.abs(

                lastCandle.close - lastCandle.open

            )



        val bodyStrength =

            (body / range)

                .coerceIn(0.0, 1.0)



        val broken =

            when (event) {


                StructureEvent.BULLISH_BOS,

                StructureEvent.BULLISH_CHOCH ->

                    lastCandle.close > swing.price



                StructureEvent.BEARISH_BOS,

                StructureEvent.BEARISH_CHOCH ->

                    lastCandle.close < swing.price



                else ->

                    false

            }



        val score =

            if (broken) {

                (bodyStrength * 0.7 + 0.3)

                    .coerceIn(0.0, 1.0)

            } else {

                bodyStrength * 0.3

            }



        return BreakQuality(

            score = score,

            confidence = score,

            valid = broken

        )

    }

}
