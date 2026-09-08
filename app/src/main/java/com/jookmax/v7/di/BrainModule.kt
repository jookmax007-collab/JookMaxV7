package com.jookmax.v7.di


import com.jookmax.v7.brain.decision.DecisionEngine
import com.jookmax.v7.brain.learning.LearningBrain
import com.jookmax.v7.brain.risk.RiskBrain

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



}