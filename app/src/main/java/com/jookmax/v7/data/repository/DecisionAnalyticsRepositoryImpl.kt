package com.jookmax.v7.data.repository


import com.jookmax.v7.brain.decision.DecisionAction
import com.jookmax.v7.brain.decision.DecisionResult

import com.jookmax.v7.brain.intelligence.IntelligenceDecision
import com.jookmax.v7.brain.intelligence.validation.ValidatedDecision

import com.jookmax.v7.core.events.DecisionEvent
import com.jookmax.v7.core.model.Symbol

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


        val decision =

            event.decision





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

            .map { entity ->





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


                        reason = "Loaded from analytics history",


                        timestamp = entity.timestamp


                    )








                val validatedDecision =


                    ValidatedDecision(


                        originalDecision = intelligenceDecision,


                        approved = entity.confidence >= 0.5,


                        finalAction =


                            if (entity.confidence >= 0.5) {


                                action


                            } else {


                                DecisionAction.HOLD


                            },


                        validationScore = entity.confidence,


                        validationReasons =


                            if (entity.confidence >= 0.5) {


                                listOf(

                                    "Loaded from analytics history"

                                )


                            } else {


                                listOf(

                                    "Historical decision below validation threshold"

                                )


                            },


                        timestamp = entity.timestamp


                    )








                DecisionEvent.DecisionGenerated(



                    symbol = Symbol(



                        code = entity.symbol



                    ),



                    decision = decision,



                    intelligenceDecision = intelligenceDecision,



                    validatedDecision = validatedDecision,



                    marketScore = entity.marketScore,



                    riskAllowed = entity.riskAllowed,



                    learningReward = entity.learningReward,



                    timestamp = entity.timestamp



                )


            }


    }









    override suspend fun clearDecisions() {


        decisionDao.clearDecisions()


    }


}