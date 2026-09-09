package com.jookmax.v7.data.mapper


import com.jookmax.v7.brain.learning.LearningExperience
import com.jookmax.v7.brain.decision.DecisionAction
import com.jookmax.v7.data.local.entity.LearningExperienceEntity


object LearningExperienceMapper {


    fun toEntity(
        experience: LearningExperience
    ): LearningExperienceEntity {


        return LearningExperienceEntity(

            decision = experience.decision.name,

            confidence = experience.confidence,

            riskApproved = experience.riskApproved,

            positionSize = experience.positionSize,


            riskScore = 0.0,


            symbol = experience.symbol,

            price = 0.0,

            timeframe = "UNKNOWN",

            marketRegime = experience.trend,


            trendState = experience.trend,

            volatilityState =
                experience.volatility.toString(),


            rsi = experience.rsi,


            macd = 0.0,


            movingAverage =
                experience.movingAverage,


            atr = 0.0,


            supportLevel = 0.0,

            resistanceLevel = 0.0,


            reward = experience.reward,


            profitLoss = 0.0,


            success = experience.success,


            holdingTime = 0L,


            drawdown = 0.0,


            schemaVersion = 1,


            brainVersion =
                experience.brainVersion,


            strategyVersion =
                experience.strategyVersion,


            featureVersion = "1",


            timestamp =
                experience.timestamp

        )

    }




    fun fromEntity(
        entity: LearningExperienceEntity
    ): LearningExperience {


        return LearningExperience(

            decision =
                DecisionAction.valueOf(
                    entity.decision
                ),


            confidence =
                entity.confidence,


            riskApproved =
                entity.riskApproved,


            positionSize =
                entity.positionSize,


            stopLoss = 0.0,


            takeProfit = 0.0,


            symbol =
                entity.symbol,


            trend =
                entity.trendState,


            rsi =
                entity.rsi,


            movingAverage =
                entity.movingAverage,


            volatility =
                entity.volatilityState
                    .toDoubleOrNull()
                    ?: 0.0,


            reward =
                entity.reward,


            success =
                entity.success,


            brainVersion =
                entity.brainVersion,


            strategyVersion =
                entity.strategyVersion,


            timestamp =
                entity.timestamp

        )

    }

}
