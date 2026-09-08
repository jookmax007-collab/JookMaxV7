package com.jookmax.v7.data.mapper


import com.jookmax.v7.brain.decision.DecisionAction
import com.jookmax.v7.brain.decision.DecisionResult
import com.jookmax.v7.core.events.DecisionEvent
import com.jookmax.v7.core.model.Symbol
import com.jookmax.v7.data.local.entity.DecisionEntity


object DecisionMapper {


    fun mapToEntity(

        event: DecisionEvent.DecisionGenerated

    ): DecisionEntity {


        return DecisionEntity(

            id = 0L,

            symbol = event.symbol.code,

            action = event.decision.action.name,

            confidence = event.decision.confidence,

            marketScore = event.decision.confidence,

            riskAllowed = true,

            learningReward = 0.0,

            timestamp = event.timestamp

        )

    }





    fun mapToEvent(

        entity: DecisionEntity

    ): DecisionEvent.DecisionGenerated {


        return DecisionEvent.DecisionGenerated(

            symbol = Symbol(

                code = entity.symbol

            ),


            decision = DecisionResult(

                action = DecisionAction.valueOf(

                    entity.action

                ),

                confidence = entity.confidence

            ),


            timestamp = entity.timestamp

        )

    }


}