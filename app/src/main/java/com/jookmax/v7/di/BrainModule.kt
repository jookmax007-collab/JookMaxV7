package com.jookmax.v7.di


import com.jookmax.v7.brain.confidence.ConfidenceFusionEngine
import com.jookmax.v7.brain.confidence.LearningConfidenceCalculator
import com.jookmax.v7.brain.confidence.MarketConfidenceCalculator
import com.jookmax.v7.brain.confidence.RiskConfidenceCalculator

import com.jookmax.v7.brain.decision.ConfidenceEngine
import com.jookmax.v7.brain.decision.DecisionEngine
import com.jookmax.v7.brain.decision.DecisionScoreCalculator
import com.jookmax.v7.brain.decision.SignalAggregator

import com.jookmax.v7.brain.learning.LearningBrain

import com.jookmax.v7.brain.risk.DynamicRiskManager
import com.jookmax.v7.brain.risk.ExposureManager
import com.jookmax.v7.brain.risk.PositionSizer
import com.jookmax.v7.brain.risk.RiskBrain
import com.jookmax.v7.brain.risk.RiskEngine
import com.jookmax.v7.brain.risk.RiskMultiplier
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
    fun provideRiskBrain(
        riskEngine: RiskEngine
    ): RiskBrain {

        return RiskBrain(
            riskEngine = riskEngine
        )

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
    fun provideExposureManager(): ExposureManager {

        return ExposureManager()

    }





    @Provides
    @Singleton
    fun provideRiskMultiplier(): RiskMultiplier {

        return RiskMultiplier()

    }





    @Provides
    @Singleton
    fun provideDynamicRiskManager(
        riskMultiplier: RiskMultiplier
    ): DynamicRiskManager {

        return DynamicRiskManager(
            riskMultiplier = riskMultiplier
        )

    }





    @Provides
    @Singleton
    fun provideRiskEngine(

        positionSizer: PositionSizer,

        stopLossCalculator: StopLossCalculator,

        takeProfitCalculator: TakeProfitCalculator,

        exposureManager: ExposureManager,

        dynamicRiskManager: DynamicRiskManager

    ): RiskEngine {


        return RiskEngine(

            positionSizer = positionSizer,

            stopLossCalculator = stopLossCalculator,

            takeProfitCalculator = takeProfitCalculator,

            exposureManager = exposureManager,

            dynamicRiskManager = dynamicRiskManager

        )

    }





    @Provides
    @Singleton
    fun provideSignalAggregator(): SignalAggregator {

        return SignalAggregator()

    }





    @Provides
    @Singleton
    fun provideDecisionScoreCalculator(): DecisionScoreCalculator {

        return DecisionScoreCalculator()

    }





    @Provides
    @Singleton
    fun provideMarketConfidenceCalculator(): MarketConfidenceCalculator {

        return MarketConfidenceCalculator()

    }





    @Provides
    @Singleton
    fun provideRiskConfidenceCalculator(): RiskConfidenceCalculator {

        return RiskConfidenceCalculator()

    }





    @Provides
    @Singleton
    fun provideLearningConfidenceCalculator(): LearningConfidenceCalculator {

        return LearningConfidenceCalculator()

    }





    @Provides
    @Singleton
    fun provideConfidenceFusionEngine(): ConfidenceFusionEngine {

        return ConfidenceFusionEngine()

    }





    @Provides
    @Singleton
    fun provideConfidenceEngine(

        marketConfidenceCalculator: MarketConfidenceCalculator,

        riskConfidenceCalculator: RiskConfidenceCalculator,

        learningConfidenceCalculator: LearningConfidenceCalculator,

        confidenceFusionEngine: ConfidenceFusionEngine

    ): ConfidenceEngine {


        return ConfidenceEngine(

            marketConfidenceCalculator = marketConfidenceCalculator,

            riskConfidenceCalculator = riskConfidenceCalculator,

            learningConfidenceCalculator = learningConfidenceCalculator,

            confidenceFusionEngine = confidenceFusionEngine

        )

    }





    @Provides
    @Singleton
    fun provideDecisionEngine(

        signalAggregator: SignalAggregator,

        decisionScoreCalculator: DecisionScoreCalculator,

        confidenceEngine: ConfidenceEngine

    ): DecisionEngine {


        return DecisionEngine(

            signalAggregator = signalAggregator,

            decisionScoreCalculator = decisionScoreCalculator,

            confidenceEngine = confidenceEngine

        )

    }





    @Provides
    @Singleton
    fun provideLearningBrain(): LearningBrain {

        return LearningBrain()

    }


}