package com.jookmax.v7.brain.learning

import com.jookmax.v7.brain.decision.DecisionAction
import com.jookmax.v7.brain.decision.DecisionResult
import com.jookmax.v7.brain.risk.RiskDecision
import com.jookmax.v7.analysis.model.MarketAnalysis


data class LearningExperience(

    val decision: DecisionAction,

    val confidence: Double,


    val riskApproved: Boolean,

    val positionSize: Double,

    val stopLoss: Double,

    val takeProfit: Double,


    val symbol: String,

    val trend: String,

    val rsi: Double,

    val movingAverage: Double,

    val volatility: Double,


    val reward: Double,

    val success: Boolean,


    val brainVersion: String = "v7",

    val strategyVersion: String = "default",


    val timestamp: Long = System.currentTimeMillis()

) {


    companion object {


        fun from(

            decisionResult: DecisionResult,

            riskDecision: RiskDecision,

            marketAnalysis: MarketAnalysis,

            reward: Double

        ): LearningExperience {


            return LearningExperience(

                decision = decisionResult.action,

                confidence = decisionResult.confidence,


                riskApproved =
                    riskDecision.positionSize > 0.0,


                positionSize =
                    riskDecision.positionSize,


                stopLoss =
                    riskDecision.stopLoss,


                takeProfit =
                    riskDecision.takeProfit,


                symbol =
                    marketAnalysis.symbol.code,


                trend =
                    marketAnalysis.trend,


                rsi =
                    marketAnalysis.rsi,


                movingAverage =
                    marketAnalysis.movingAverage,


                volatility =
                    marketAnalysis.volatility,


                reward = reward,

                success =
                    reward > 0.0

            )

        }

    }

}
