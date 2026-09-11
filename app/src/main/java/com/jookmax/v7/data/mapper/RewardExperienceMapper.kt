package com.jookmax.v7.data.mapper


import com.jookmax.v7.brain.decision.DecisionAction
import com.jookmax.v7.brain.reward.model.RewardExperience
import com.jookmax.v7.data.local.entity.RewardExperienceEntity



object RewardExperienceMapper {



    fun toEntity(

        experience: RewardExperience

    ): RewardExperienceEntity {



        return RewardExperienceEntity(


            id = experience.id,


            action = experience.action.name,


            entryPrice = experience.entryPrice,


            exitPrice = experience.exitPrice,


            reward = experience.reward,


            success = experience.success,


            reason = experience.reason,



            confidence = experience.confidence,


            positionSize = experience.positionSize,


            stopLoss = experience.stopLoss,


            takeProfit = experience.takeProfit,


            symbol = experience.symbol,


            trend = experience.trend,


            rsi = experience.rsi,


            movingAverage = experience.movingAverage,


            volatility = experience.volatility,



            timestamp = experience.timestamp

        )

    }






    fun toDomain(

        entity: RewardExperienceEntity

    ): RewardExperience {



        return RewardExperience(


            id = entity.id,


            action = DecisionAction.valueOf(

                entity.action

            ),


            entryPrice = entity.entryPrice,


            exitPrice = entity.exitPrice,


            reward = entity.reward,


            success = entity.success,


            reason = entity.reason,



            confidence = entity.confidence,


            positionSize = entity.positionSize,


            stopLoss = entity.stopLoss,


            takeProfit = entity.takeProfit,


            symbol = entity.symbol,


            trend = entity.trend,


            rsi = entity.rsi,


            movingAverage = entity.movingAverage,


            volatility = entity.volatility,


            timestamp = entity.timestamp

        )

    }

}