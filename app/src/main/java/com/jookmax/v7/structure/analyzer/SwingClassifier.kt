package com.jookmax.v7.structure.analyzer


import com.jookmax.v7.structure.model.SwingPoint
import com.jookmax.v7.structure.model.SwingType

import javax.inject.Inject
import javax.inject.Singleton



enum class SwingClassification {

    HH,

    HL,

    LH,

    LL,

    NONE

}





@Singleton
class SwingClassifier @Inject constructor() {



    fun classify(

        swings: List<SwingPoint>

    ): SwingClassification {



        if (swings.size < 2) {

            return SwingClassification.NONE

        }



        val previous =

            swings[swings.size - 2]



        val current =

            swings.last()





        return when {



            current.type == SwingType.HIGH &&

            previous.type == SwingType.HIGH &&

            current.price > previous.price ->


                SwingClassification.HH





            current.type == SwingType.HIGH &&

            previous.type == SwingType.HIGH &&

            current.price < previous.price ->


                SwingClassification.LH





            current.type == SwingType.LOW &&

            previous.type == SwingType.LOW &&

            current.price > previous.price ->


                SwingClassification.HL





            current.type == SwingType.LOW &&

            previous.type == SwingType.LOW &&

            current.price < previous.price ->


                SwingClassification.LL





            else ->


                SwingClassification.NONE


        }


    }


}