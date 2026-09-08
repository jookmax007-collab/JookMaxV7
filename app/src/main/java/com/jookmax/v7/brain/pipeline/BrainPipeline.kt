package com.jookmax.v7.brain.pipeline


import com.jookmax.v7.analysis.model.MarketAnalysis

import com.jookmax.v7.brain.confidence.ConfidenceFeedbackCollector

import com.jookmax.v7.brain.decision.DecisionEngine

import com.jookmax.v7.brain.intelligence.IntelligenceEngine
import com.jookmax.v7.brain.intelligence.feedback.IntelligenceFeedbackBridge

import com.jookmax.v7.brain.learning.LearningBrain
import com.jookmax.v7.brain.learning.LearningExperience
import com.jookmax.v7.brain.learning.LearningExperienceManager

import com.jookmax.v7.brain.market.MarketBrain

import com.jookmax.v7.brain.risk.RiskEngine
import com.jookmax.v7.brain.risk.RiskProfile


import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class BrainPipeline @Inject constructor(


    private val marketBrain: MarketBrain,


    private val riskEngine: RiskEngine,


    private val learningBrain: LearningBrain,


    private val decisionEngine: DecisionEngine,


    private val confidenceFeedbackCollector: ConfidenceFeedbackCollector,


    private val learningExperienceManager: LearningExperienceManager,


    private val intelligenceEngine: IntelligenceEngine,


    private val intelligenceFeedbackBridge: IntelligenceFeedbackBridge


) {



    fun execute(): BrainExecutionResult {



        val context = createContext()





        val marketScore =

            calculateMarketScore(

                context.marketAnalysis

            )





        val riskAllowed =

            context.riskDecision.positionSize > 0.0





        val decision =

            decisionEngine.decide(

                marketScore = marketScore,

                riskAllowed = riskAllowed,

                learningReward = context.learningReward,

                riskDecision = context.riskDecision

            )







        confidenceFeedbackCollector.collect(

            decisionResult = decision,

            reward = context.learningReward

        )







        val experience =

            LearningExperience.from(

                decisionResult = decision,

                riskDecision = context.riskDecision,

                reward = context.learningReward

            )





        learningExperienceManager.addExperience(

            experience

        )







        val intelligenceDecision =

            intelligenceEngine.evaluate(

                decisionResult = decision

            )







        intelligenceFeedbackBridge.recordDecision(

            decision = intelligenceDecision,

            reward = context.learningReward

        )







        val finalContext =

            context.copy(

                decisionResult = decision

            )







        return BrainExecutionResult(

            context = finalContext,

            decision = decision,

            intelligenceDecision = intelligenceDecision

        )

    }









    private fun createContext(): BrainContext {



        val marketAnalysis =

            marketBrain.analyze()





        val riskDecision =

            riskEngine.calculateTradeRisk(

                profile = RiskProfile(),

                entryPrice = 0.0,

                volatility = marketAnalysis.volatility,

                isLong = marketAnalysis.trend == "BULLISH"

            )







        val learningReward =

            learningBrain

                .getLastResult()

                ?.reward

                ?: 0.0





        return BrainContext(

            marketAnalysis = marketAnalysis,

            riskDecision = riskDecision,

            learningReward = learningReward

        )

    }









    private fun calculateMarketScore(

        analysis: MarketAnalysis

    ): Double {



        return when {



            analysis.trend == "BULLISH" &&

                    analysis.rsi < 70 ->

                0.8





            analysis.trend == "BEARISH" &&

                    analysis.rsi > 30 ->

                0.2





            else ->

                0.5

        }

    }


}