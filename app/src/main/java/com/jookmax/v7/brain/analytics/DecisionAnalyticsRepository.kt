package com.jookmax.v7.brain.analytics


import com.jookmax.v7.data.local.dao.DecisionDao
import com.jookmax.v7.data.local.entity.DecisionEntity

import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class DecisionAnalyticsRepository @Inject constructor(


    private val decisionDao: DecisionDao


) {



    suspend fun saveDecision(

        record: DecisionRecord

    ) {



        val entity = DecisionEntity(


            id = record.id,


            symbol = record.symbol,


            action = record.action,


            confidence = record.confidence,


            marketScore = record.marketScore,


            riskAllowed = record.riskAllowed,


            learningReward = record.learningReward,


            timestamp = record.timestamp


        )



        decisionDao.insertDecision(

            entity

        )

    }







    suspend fun getDecisions(): List<DecisionRecord> {



        return decisionDao
            .getDecisions()
            .map {



                DecisionRecord(


                    id = it.id,


                    symbol = it.symbol,


                    action = it.action,


                    confidence = it.confidence,


                    marketScore = it.marketScore,


                    riskAllowed = it.riskAllowed,


                    learningReward = it.learningReward,


                    timestamp = it.timestamp


                )


            }


    }





    suspend fun clear() {


        decisionDao.clearDecisions()


    }



}