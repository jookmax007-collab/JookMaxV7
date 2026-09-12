package com.jookmax.v7.structure


import com.jookmax.v7.core.model.MarketCandle
import com.jookmax.v7.structure.analyzer.SwingClassifier
import com.jookmax.v7.structure.analyzer.StructureAnalyzer
import com.jookmax.v7.structure.analyzer.BreakQualityAnalyzer
import com.jookmax.v7.structure.analyzer.SwingClassification
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


    private val swingClassifier: SwingClassifier,

    private val structureAnalyzer: StructureAnalyzer,

    private val breakQualityAnalyzer: BreakQualityAnalyzer,

    private val bosDetector: BOSDetector,


    private val chochDetector: CHoCHDetector


) {



    fun analyze(

        candles: List<MarketCandle>

    ): MarketStructure {



        val swings =

            swingDetector.detect(candles)





        val swingClassification =

            swingClassifier.classify(

                swings

            )





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


        val structureResult =

            structureAnalyzer.analyze(

                classification = swingClassification,

                bosDetected = bos,

                chochDetected = choch

            )





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






        val breakSwing =

            when (structureEvent) {

                StructureEvent.BULLISH_BOS,
                StructureEvent.BULLISH_CHOCH ->

                    lastHigh


                StructureEvent.BEARISH_BOS,
                StructureEvent.BEARISH_CHOCH ->

                    lastLow


                else ->

                    null

            }



        val breakQuality =

            breakQualityAnalyzer.analyze(

                candles = candles,

                swing = breakSwing,

                event = structureEvent

            )

        val direction =

            when {


                structureEvent == StructureEvent.BULLISH_BOS ||

                structureEvent == StructureEvent.BULLISH_CHOCH ->

                    StructureDirection.BULLISH





                structureEvent == StructureEvent.BEARISH_BOS ||

                structureEvent == StructureEvent.BEARISH_CHOCH ->

                    StructureDirection.BEARISH





                swingClassification == SwingClassification.HH ||

                swingClassification == SwingClassification.HL ->

                    StructureDirection.BULLISH



                swingClassification == SwingClassification.LH ||

                swingClassification == SwingClassification.LL ->

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

            swingClassification = swingClassification,

            pattern = structureResult.pattern,

            strength = structureResult.strength,

            breakQuality = breakQuality,

            structureEvent = structureEvent,


            timestamp =

                candles.lastOrNull()?.timestamp ?: 0L


        )

    }


}


















