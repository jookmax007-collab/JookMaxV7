package com.jookmax.v7.di


import com.jookmax.v7.brain.BrainManager
import com.jookmax.v7.brain.decision.DecisionEngine
import com.jookmax.v7.brain.learning.LearningBrain
import com.jookmax.v7.brain.market.MarketBrain
import com.jookmax.v7.brain.risk.RiskBrain

import com.jookmax.v7.core.events.EventBus
import com.jookmax.v7.core.logging.Logger

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
object BrainModule {





    @Provides
    @Singleton
    fun provideMarketBrain(): MarketBrain {


        return MarketBrain()


    }








    @Provides
    @Singleton
    fun provideRiskBrain(): RiskBrain {


        return RiskBrain()


    }








    @Provides
    @Singleton
    fun provideDecisionEngine(): DecisionEngine {


        return DecisionEngine()


    }








    @Provides
    @Singleton
    fun provideLearningBrain(): LearningBrain {


        return LearningBrain()


    }








    @Provides
    @Singleton
    fun provideBrainManager(


        marketBrain: MarketBrain,


        riskBrain: RiskBrain,


        decisionEngine: DecisionEngine,


        learningBrain: LearningBrain,


        logger: Logger,


        eventBus: EventBus



    ): BrainManager {



        return BrainManager(


            marketBrain = marketBrain,


            riskBrain = riskBrain,


            decisionEngine = decisionEngine,


            learningBrain = learningBrain,


            logger = logger,


            eventBus = eventBus



        )


    }



}