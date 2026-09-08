package com.jookmax.v7.di


import com.jookmax.v7.brain.decision.DecisionEngine
import com.jookmax.v7.brain.learning.LearningBrain
import com.jookmax.v7.brain.risk.RiskBrain
import com.jookmax.v7.brain.risk.RiskEngine
import com.jookmax.v7.brain.risk.PositionSizer
import com.jookmax.v7.brain.risk.StopLossCalculator
import com.jookmax.v7.brain.risk.TakeProfitCalculator

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
    fun providePositionSizer(): PositionSizer {

        return PositionSizer()

    }





    @Provides
    @Singleton
    fun provideStopLossCalculator(): StopLossCalculator {

        return StopLossCalculator()

    }





    @Provides
    @Singleton
    fun provideTakeProfitCalculator(): TakeProfitCalculator {

        return TakeProfitCalculator()

    }





    @Provides
    @Singleton
    fun provideRiskEngine(

        positionSizer: PositionSizer,

        stopLossCalculator: StopLossCalculator,

        takeProfitCalculator: TakeProfitCalculator

    ): RiskEngine {


        return RiskEngine(

            positionSizer = positionSizer,

            stopLossCalculator = stopLossCalculator,

            takeProfitCalculator = takeProfitCalculator

        )

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