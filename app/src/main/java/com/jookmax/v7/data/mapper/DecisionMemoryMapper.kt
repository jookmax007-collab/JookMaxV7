package com.jookmax.v7.data.mapper


import com.jookmax.v7.brain.intelligence.memory.DecisionPattern
import com.jookmax.v7.brain.decision.DecisionAction
import com.jookmax.v7.data.local.entity.DecisionMemoryEntity


object DecisionMemoryMapper {


    fun toEntity(
        pattern: DecisionPattern
    ): DecisionMemoryEntity {


        return DecisionMemoryEntity(

            symbol = pattern.symbol,

            trend = pattern.trend,

            rsi = pattern.rsi,

            volatility = pattern.volatility,

            action = pattern.action.name,

            confidence = pattern.confidence,

            approved = pattern.approved,

            reward = pattern.reward,

            timestamp = pattern.timestamp

        )

    }



    fun fromEntity(
        entity: DecisionMemoryEntity
    ): DecisionPattern {


        return DecisionPattern(

            symbol = entity.symbol,

            trend = entity.trend,

            rsi = entity.rsi,

            volatility = entity.volatility,

            action =
                DecisionAction.valueOf(
                    entity.action
                ),

            confidence = entity.confidence,

            approved = entity.approved,

            reward = entity.reward,

            timestamp = entity.timestamp

        )

    }

}
