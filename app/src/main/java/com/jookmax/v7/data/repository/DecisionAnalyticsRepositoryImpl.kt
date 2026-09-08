package com.jookmax.v7.data.repository


import com.jookmax.v7.brain.decision.DecisionAction
import com.jookmax.v7.brain.decision.DecisionResult
import com.jookmax.v7.brain.intelligence.IntelligenceDecision
import com.jookmax.v7.core.events.DecisionEvent
import com.jookmax.v7.core.model.Symbol
import com.jookmax.v7.data.local.entity.DecisionEntity
import com.jookmax.v7.data.local.dao.DecisionDao
import com.jookmax.v7.domain.analytics.DecisionAnalyticsRepository

import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class DecisionAnalyticsRepositoryImpl @Inject constructor(


    private val decisionDao: DecisionDao


) : DecisionAnalyticsRepository {





    override suspend fun saveDecision(

        event: DecisionEvent.DecisionGenerated

    ) {


        val decision = event.decision



        decisionDao.insertDecision(



            DecisionEntity(


                symbol = event.symbol.code,


                action = decision.action.name,


                confidence = decision.confidence,


                marketScore = event.marketScore,


                riskAllowed = event.riskAllowed,


                learningReward = event.learningReward,


                timestamp = event.timestamp


            )


        )


    }








    override suspend fun getDecisions():

            List<DecisionEvent.DecisionGenerated> {



        return decisionDao

            .getDecisions()

            .map {



                val action =

                    DecisionAction.valueOf(

                        it.action

                    )





                val decision =

                    DecisionResult(

                        action = action,

                        confidence = it.confidence

                    )





                val intelligenceDecision =

                    IntelligenceDecision(

                        action = action,

                        confidence = it.confidence,

                        reason = "Loaded from analytics history",

                        timestamp = it.timestamp

                    )





                DecisionEvent.DecisionGenerated(



                    symbol = Symbol(

                        code = it.symbol

                    ),



                    decision = decision,



                    intelligenceDecision = intelligenceDecision,



                    marketScore = it.marketScore,



                    riskAllowed = it.riskAllowed,



                    learningReward = it.learningReward,



                    timestamp = it.timestamp



                )


            }


    }








    override suspend fun clearDecisions() {


        decisionDao.clearDecisions()


    }


}