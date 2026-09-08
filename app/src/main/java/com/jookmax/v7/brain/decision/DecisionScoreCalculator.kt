package com.jookmax.v7.brain.decision


import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class DecisionScoreCalculator @Inject constructor() {



    fun calculate(

        signal: AggregatedSignal

    ): DecisionScore {



        val normalizedScore =

            signal.score

                .coerceIn(0.0, 1.0)





        val direction =

            when {


                normalizedScore >= 0.7 ->

                    DecisionDirection.BULLISH



                normalizedScore <= 0.3 ->

                    DecisionDirection.BEARISH



                else ->

                    DecisionDirection.NEUTRAL

            }





        return DecisionScore(

            score = normalizedScore,

            direction = direction,

            riskApproved = signal.riskApproved

        )

    }


}



data class DecisionScore(

    val score: Double,

    val direction: DecisionDirection,

    val riskApproved: Boolean

)



enum class DecisionDirection {

    BULLISH,

    BEARISH,

    NEUTRAL

}
