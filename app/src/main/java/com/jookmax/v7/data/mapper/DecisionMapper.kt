package com.jookmax.v7.data.mapper


import com.jookmax.v7.brain.decision.DecisionAction
import com.jookmax.v7.brain.decision.DecisionResult
import com.jookmax.v7.brain.intelligence.IntelligenceDecision
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


            marketScore = event.marketScore,


            riskAllowed = event.riskAllowed,


            learningReward = event.learningReward,


            timestamp = event.timestamp


        )

    }








    fun mapToEvent(

        entity: DecisionEntity

    ): DecisionEvent.DecisionGenerated {



        val action =

            DecisionAction.valueOf(

                entity.action

            )





        val decision =

            DecisionResult(

                action = action,

                confidence = entity.confidence

            )





        val intelligenceDecision =

            IntelligenceDecision(

                action = action,

                confidence = entity.confidence,

                reason = "Restored from decision history",

                timestamp = entity.timestamp

            )







        return DecisionEvent.DecisionGenerated(



            symbol = Symbol(

                code = entity.symbol

            ),



            decision = decision,



            intelligenceDecision = intelligenceDecision,



            marketScore = entity.marketScore,



            riskAllowed = entity.riskAllowed,



            learningReward = entity.learningReward,



            timestamp = entity.timestamp



        )

    }


}