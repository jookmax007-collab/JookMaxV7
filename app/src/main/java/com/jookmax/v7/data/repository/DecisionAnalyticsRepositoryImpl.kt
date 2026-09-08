package com.jookmax.v7.data.repository


import com.jookmax.v7.core.events.DecisionEvent

import com.jookmax.v7.data.local.dao.DecisionDao
import com.jookmax.v7.data.local.entity.DecisionEntity

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


                DecisionEvent.DecisionGenerated(


                    symbol = com.jookmax.v7.core.model.Symbol(

                        code = it.symbol

                    ),


                    decision =
                        com.jookmax.v7.brain.decision.DecisionResult(

                            action =
                                com.jookmax.v7.brain.decision.DecisionAction.valueOf(

                                    it.action

                                ),


                            confidence = it.confidence

                        ),


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