package com.jookmax.v7.structure.analyzer


import com.jookmax.v7.structure.model.StructurePattern
import com.jookmax.v7.structure.model.StructureStrength
import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class StructureAnalyzer @Inject constructor(

    private val strengthCalculator: StructureStrengthCalculator

) {



    fun analyze(

        classification: SwingClassification,

        bosDetected: Boolean,

        chochDetected: Boolean

    ): StructureResult {



        val pattern = when {


            classification == SwingClassification.HH ->

                StructurePattern.BULLISH_STRUCTURE



            classification == SwingClassification.HL ->

                StructurePattern.BULLISH_STRUCTURE



            classification == SwingClassification.LH ->

                StructurePattern.BEARISH_STRUCTURE



            classification == SwingClassification.LL ->

                StructurePattern.BEARISH_STRUCTURE



            else ->

                StructurePattern.UNKNOWN

        }





        val strength =

            strengthCalculator.calculate(

                classification = classification,

                bosDetected = bosDetected,

                chochDetected = chochDetected

            )





        return StructureResult(

            pattern = pattern,

            strength = strength

        )

    }


}





data class StructureResult(

    val pattern: StructurePattern,

    val strength: StructureStrength

)
