package com.jookmax.v7.structure.analyzer


import com.jookmax.v7.structure.model.StructureStrength
import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class StructureStrengthCalculator @Inject constructor() {



    fun calculate(

        classification: SwingClassification,

        bosDetected: Boolean,

        chochDetected: Boolean

    ): StructureStrength {



        var score = 0.0



        when (classification) {


            SwingClassification.HH,

            SwingClassification.LL -> {

                score += 0.30

            }



            SwingClassification.HL,

            SwingClassification.LH -> {

                score += 0.20

            }



            SwingClassification.NONE -> {

                score += 0.0

            }

        }





        if (bosDetected) {

            score += 0.30

        }





        if (chochDetected) {

            score += 0.20

        }





        val confidence =

            score.coerceIn(

                0.0,

                1.0

            )





        return StructureStrength(

            score = confidence,

            confidence = confidence

        )

    }


}
