package com.jookmax.v7.brain.pipeline


import com.jookmax.v7.brain.decision.DecisionEngine
import com.jookmax.v7.brain.learning.LearningBrain
import com.jookmax.v7.brain.market.MarketBrain
import com.jookmax.v7.brain.risk.RiskBrain

import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class BrainPipeline @Inject constructor(


    private val marketBrain: MarketBrain,


    private val riskBrain: RiskBrain,


    private val learningBrain: LearningBrain,


    private val decisionEngine: DecisionEngine


) {



    fun execute(): BrainContext {


        val context = createContext()



        val decision = decisionEngine.decide(


            marketScore =

                context.marketAnalysis.confidence,



            riskAllowed =

                context.riskResult.allowed,



            learningReward =

                context.learningReward


        )




        return context.copy(

            decisionResult = decision

        )


    }









    private fun createContext(): BrainContext {



        val marketAnalysis =

            marketBrain.analyze()





        val riskResult =

            riskBrain.evaluateRisk(

                marketVolatility = 0.5

            )







        val learningReward =

            learningBrain
                .getLastResult()
                ?.reward
                ?: 0.0





        return BrainContext(


            marketAnalysis = marketAnalysis,


            riskResult = riskResult,


            learningReward = learningReward


        )


    }



}