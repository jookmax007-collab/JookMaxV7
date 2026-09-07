package com.jookmax.v7.brain


import com.jookmax.v7.brain.decision.DecisionEngine
import com.jookmax.v7.brain.learning.LearningBrain
import com.jookmax.v7.brain.market.MarketBrain
import com.jookmax.v7.brain.risk.RiskBrain
import com.jookmax.v7.core.logging.Logger

import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class BrainManager @Inject constructor(


    private val marketBrain: MarketBrain,


    private val riskBrain: RiskBrain,


    private val decisionEngine: DecisionEngine,


    private val learningBrain: LearningBrain,


    private val logger: Logger


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









    fun process() {



        if (!initialized) {



            logger.warning(

                tag = "BrainManager",

                message = "Process ignored. Brain is not initialized"

            )



            return

        }





        logger.debug(

            tag = "BrainManager",

            message = "Brain processing pipeline started"

        )





        // Future pipeline:

        // 1. Market analysis

        // 2. Risk evaluation

        // 3. Decision generation

        // 4. Learning update





        logger.debug(

            tag = "BrainManager",

            message = "Brain processing pipeline completed"

        )


    }



}