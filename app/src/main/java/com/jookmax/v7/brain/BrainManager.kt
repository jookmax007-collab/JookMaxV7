package com.jookmax.v7.brain


import com.jookmax.v7.brain.decision.DecisionEngine
import com.jookmax.v7.brain.learning.LearningBrain
import com.jookmax.v7.brain.market.MarketBrain
import com.jookmax.v7.brain.pipeline.BrainPipeline
import com.jookmax.v7.brain.risk.RiskBrain

import com.jookmax.v7.core.events.DecisionEvent
import com.jookmax.v7.core.events.EventBus

import com.jookmax.v7.core.logging.Logger
import com.jookmax.v7.core.model.Symbol


import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class BrainManager @Inject constructor(


    private val marketBrain: MarketBrain,


    private val riskBrain: RiskBrain,


    private val decisionEngine: DecisionEngine,


    private val learningBrain: LearningBrain,


    private val logger: Logger,


    private val eventBus: EventBus,


    private val brainPipeline: BrainPipeline


) {



    private var initialized = false






    fun initialize() {


        if (initialized) {


            logger.warning(

                tag = "BrainManager",

                message = "Initialize ignored. Brain already initialized"

            )


            return

        }





        initialized = true





        logger.info(

            tag = "BrainManager",

            message = "Brain system initialized"

        )


    }








    fun isReady(): Boolean {


        return initialized


    }









    fun process() {



        if (!initialized) {


            logger.warning(

                tag = "BrainManager",

                message = "Process ignored. Brain is not initialized"

            )


            return

        }








        val executionResult =
            kotlinx.coroutines.runBlocking {
                brainPipeline.execute()
            }







        val context =

            executionResult.context







        val decision =

            executionResult.decision







        val intelligenceDecision =

            executionResult.intelligenceDecision





        val validatedDecision =

            executionResult.validatedDecision







        logger.info(


            tag = "BrainManager",


            message =

                "Decision generated: ${decision.action} | " +

                "Intelligence confidence: ${intelligenceDecision.confidence} | " +

                "Validation: ${validatedDecision.approved}"

        )









        val marketScore =


            when {


                context.marketAnalysis.trend == "BULLISH" ->

                    0.8



                context.marketAnalysis.trend == "BEARISH" ->

                    0.2



                else ->

                    0.5


            }












        eventBus.publish(



            DecisionEvent.DecisionGenerated(



                symbol = Symbol(



                    code = "XAUUSD",



                    description = "Gold vs US Dollar"



                ),





                decision = decision,





                intelligenceDecision = intelligenceDecision,





                validatedDecision = validatedDecision,





                marketScore = marketScore,





                riskAllowed =

                    context.riskDecision.positionSize > 0.0,





                learningReward =

                    context.learningReward



            )



        )



    }
















    fun shutdown() {



        logger.info(

            tag = "BrainManager",

            message = "Brain shutdown started"

        )











        marketBrain.reset()



        riskBrain.reset()



        decisionEngine.reset()



        learningBrain.reset()







        initialized = false










        logger.info(

            tag = "BrainManager",

            message = "Brain shutdown completed"

        )


    }


}
