package com.jookmax.v7.brain.structure


import com.jookmax.v7.structure.model.MarketStructure
import com.jookmax.v7.structure.model.StructureDirection

import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class StructureScoreCalculator @Inject constructor() {



    fun calculate(

        structure: MarketStructure

    ): Double {


        return when {


            structure.direction == StructureDirection.BULLISH
                    &&
            structure.bosDetected ->

                0.25



            structure.direction == StructureDirection.BEARISH
                    &&
            structure.chochDetected ->

                -0.25



            structure.direction == StructureDirection.BULLISH ->

                0.15



            structure.direction == StructureDirection.BEARISH ->

                -0.15



            else ->

                0.0

        }


    }


}