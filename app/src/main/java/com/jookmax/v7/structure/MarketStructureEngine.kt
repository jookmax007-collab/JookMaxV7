package com.jookmax.v7.structure


import com.jookmax.v7.core.model.MarketCandle
import com.jookmax.v7.structure.detector.BOSDetector
import com.jookmax.v7.structure.detector.CHoCHDetector
import com.jookmax.v7.structure.detector.SwingDetector
import com.jookmax.v7.structure.model.MarketStructure
import com.jookmax.v7.structure.model.StructureDirection
import com.jookmax.v7.structure.model.StructureEvent
import com.jookmax.v7.structure.model.SwingType

import javax.inject.Inject



class MarketStructureEngine @Inject constructor(


    private val swingDetector: SwingDetector,


    private val bosDetector: BOSDetector,


    private val chochDetector: CHoCHDetector


) {



    fun analyze(

        candles: List<MarketCandle>

    ): MarketStructure {



        val swings =

            swingDetector.detect(candles)





        val bosEvent =

            bosDetector.detectEvent(

                candles,

                swings

            )





        val chochEvent =

            chochDetector.detectEvent(

                candles,

                swings

            )





        val structureEvent =

            when {


                bosEvent != StructureEvent.NONE ->

                    bosEvent



                chochEvent != StructureEvent.NONE ->

                    chochEvent



                else ->

                    StructureEvent.NONE

            }





        val bos =

            bosEvent != StructureEvent.NONE





        val choch =

            chochEvent != StructureEvent.NONE





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





        val direction =

            when {


                structureEvent == StructureEvent.BULLISH_BOS ||

                structureEvent == StructureEvent.BULLISH_CHOCH ->

                    StructureDirection.BULLISH





                structureEvent == StructureEvent.BEARISH_BOS ||

                structureEvent == StructureEvent.BEARISH_CHOCH ->

                    StructureDirection.BEARISH





                else ->

                    StructureDirection.UNKNOWN

            }





        return MarketStructure(


            direction = direction,


            lastSwingHigh = lastHigh,


            lastSwingLow = lastLow,


            bosDetected = bos,


            chochDetected = choch,


            structureEvent = structureEvent,


            timestamp =

                candles.lastOrNull()?.timestamp ?: 0L


        )

    }


}