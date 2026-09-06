package com.jookmax.v7.brain.decision


class DecisionEngine {


    fun decide(
        marketScore: Double,
        riskAllowed: Boolean,
        learningReward: Double
    ): DecisionResult {


        return when {


            !riskAllowed -> {

                DecisionResult(
                    action = DecisionAction.HOLD,
                    confidence = 0.0
                )

            }



            marketScore >= 0.7 && learningReward >= 0 -> {

                DecisionResult(
                    action = DecisionAction.BUY,
                    confidence = marketScore
                )

            }



            marketScore <= 0.3 && learningReward >= 0 -> {

                DecisionResult(
                    action = DecisionAction.SELL,
                    confidence = 1 - marketScore
                )

            }



            else -> {

                DecisionResult(
                    action = DecisionAction.HOLD,
                    confidence = 0.5
                )

            }
        }
    }



    fun reset() {

        // Future:
        // clear decision history

    }

}




enum class DecisionAction {

    BUY,

    SELL,

    HOLD

}




data class DecisionResult(

    val action: DecisionAction,

    val confidence: Double

)