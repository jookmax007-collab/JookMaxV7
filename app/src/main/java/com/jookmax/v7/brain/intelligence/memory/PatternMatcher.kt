package com.jookmax.v7.brain.intelligence.memory


import javax.inject.Inject
import javax.inject.Singleton

import kotlin.math.abs



@Singleton
class PatternMatcher @Inject constructor() {



    companion object {

        /**
         * Minimum similarity required
         * to consider a memory relevant.
         */
        private const val MIN_SIMILARITY = 0.70

    }




    fun match(

        current: DecisionPattern,

        history: List<DecisionPattern>

    ): List<DecisionPattern> {


        return history

            .map { pattern ->


                pattern to calculateSimilarity(

                    current,

                    pattern

                )


            }


            .filter {

                it.second >= MIN_SIMILARITY

            }


            .sortedByDescending {

                it.second

            }


            .map {

                it.first

            }


    }






    private fun calculateSimilarity(

        current: DecisionPattern,

        historical: DecisionPattern

    ): Double {


        var score = 0.0




        // Symbol similarity

        if (

            current.symbol == historical.symbol

        ) {

            score += 0.25

        }





        // Trend similarity

        if (

            current.trend == historical.trend

        ) {

            score += 0.25

        }





        // RSI similarity

        val rsiSimilarity =

            1.0 -

            (

                abs(

                    current.rsi -

                    historical.rsi

                )

                / 100.0

            )



        score +=

            rsiSimilarity.coerceIn(

                0.0,

                1.0

            ) * 0.15





        // Volatility similarity

        val volatilityDifference =

            abs(

                current.volatility -

                historical.volatility

            )



        val volatilitySimilarity =

            1.0 -

            volatilityDifference.coerceAtMost(

                1.0

            )



        score +=

            volatilitySimilarity * 0.15





        // Action similarity

        if (

            current.action == historical.action

        ) {

            score += 0.10

        }





        // Confidence similarity

        val confidenceSimilarity =

            1.0 -

            abs(

                current.confidence -

                historical.confidence

            )



        score +=

            confidenceSimilarity.coerceIn(

                0.0,

                1.0

            ) * 0.10





        return score.coerceIn(

            0.0,

            1.0

        )

    }


}