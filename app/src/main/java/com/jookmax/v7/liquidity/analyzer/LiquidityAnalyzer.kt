package com.jookmax.v7.liquidity.analyzer


import com.jookmax.v7.core.model.MarketCandle
import com.jookmax.v7.liquidity.detector.EqualHighDetector
import com.jookmax.v7.liquidity.detector.EqualLowDetector
import com.jookmax.v7.liquidity.detector.FakeBreakoutDetector
import com.jookmax.v7.liquidity.detector.LiquiditySweepDetector
import com.jookmax.v7.liquidity.detector.StopHuntDetector
import com.jookmax.v7.liquidity.model.LiquidityEvent
import com.jookmax.v7.liquidity.model.LiquidityAnalysisResult

import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class LiquidityAnalyzer @Inject constructor(


    private val equalHighDetector: EqualHighDetector,


    private val equalLowDetector: EqualLowDetector,


    private val liquiditySweepDetector: LiquiditySweepDetector,


    private val stopHuntDetector: StopHuntDetector,


    private val fakeBreakoutDetector: FakeBreakoutDetector


) {



    fun analyze(

        candles: List<MarketCandle>,

        liquidityLevel: Double

    ): LiquidityAnalysisResult {



        if (candles.isEmpty())

            return LiquidityAnalysisResult()



        val events = mutableListOf<LiquidityEvent>()





        val equalHigh =

            equalHighDetector.detect(

                candles

            )



        if (equalHigh != null)

            events.add(

                LiquidityEvent.EQUAL_HIGH_FOUND

            )





        val equalLow =

            equalLowDetector.detect(

                candles

            )



        if (equalLow != null)

            events.add(

                LiquidityEvent.EQUAL_LOW_FOUND

            )





        val sweep =

            liquiditySweepDetector.detect(

                candles,

                liquidityLevel

            )



        if (sweep != LiquidityEvent.NONE)

            events.add(sweep)





        val stopHunt =

            stopHuntDetector.detect(

                candles,

                liquidityLevel

            )



        if (stopHunt != LiquidityEvent.NONE)

            events.add(stopHunt)





        val fakeBreakout =

            fakeBreakoutDetector.detect(

                candles,

                liquidityLevel

            )



        if (fakeBreakout != LiquidityEvent.NONE)

            events.add(fakeBreakout)





        return LiquidityAnalysisResult(

            events = events,

            score = calculateScore(events)

        )

    }





    private fun calculateScore(

        events: List<LiquidityEvent>

    ): Double {



        var score = 0.0



        events.forEach { event ->


            score += when(event) {


                LiquidityEvent.LIQUIDITY_SWEEP -> 0.25


                LiquidityEvent.STOP_HUNT -> 0.30


                LiquidityEvent.FAKE_BREAKOUT -> 0.25


                LiquidityEvent.EQUAL_HIGH_FOUND -> 0.10


                LiquidityEvent.EQUAL_LOW_FOUND -> 0.10


                else -> 0.0

            }

        }



        return score.coerceAtMost(1.0)

    }

}




