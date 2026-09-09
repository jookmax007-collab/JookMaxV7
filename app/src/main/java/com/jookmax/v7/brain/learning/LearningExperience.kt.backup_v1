package com.jookmax.v7.brain.learning


import com.jookmax.v7.brain.decision.DecisionAction
import com.jookmax.v7.brain.decision.DecisionResult
import com.jookmax.v7.brain.risk.RiskDecision



/**
 * Learning Experience
 *
 * Stores one complete brain cycle result.
 *
 * Flow:
 *
 * Market State
 *      +
 * Risk Decision
 *      +
 * Brain Decision
 *      +
 * Confidence
 *      +
 * Reward
 *
 *        |
 *        v
 *
 * Learning Memory
 *
 */
data class LearningExperience(


    val decision: DecisionAction,


    val confidence: Double,


    val riskApproved: Boolean,


    val positionSize: Double,


    val reward: Double,


    val success: Boolean,


    val timestamp: Long = System.currentTimeMillis()


) {



    companion object {



        fun from(

            decisionResult: DecisionResult,

            riskDecision: RiskDecision,

            reward: Double

        ): LearningExperience {



            return LearningExperience(


                decision =

                    decisionResult.action,



                confidence =

                    decisionResult.confidence,



                riskApproved =

                    riskDecision.positionSize > 0.0,



                positionSize =

                    riskDecision.positionSize,



                reward = reward,



                success =

                    reward > 0.0

            )

        }

    }


}