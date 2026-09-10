package com.jookmax.v7.data.backup


import com.jookmax.v7.data.local.entity.DecisionMemoryEntity
import com.jookmax.v7.data.local.entity.DecisionPatternEntity
import com.jookmax.v7.data.local.entity.LearningExperienceEntity

import org.json.JSONArray
import org.json.JSONObject

import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class BackupSerializer @Inject constructor() {



    fun serialize(
        snapshot: BackupSnapshot
    ): String {


        val root = JSONObject()


        root.put(
            "metadata",
            serializeMetadata(snapshot.metadata)
        )


        root.put(
            "decisionMemory",
            serializeDecisionMemory(snapshot.decisionMemory)
        )


        root.put(
            "decisionPatterns",
            serializeDecisionPatterns(snapshot.decisionPatterns)
        )


        root.put(
            "learningExperiences",
            serializeLearningExperiences(snapshot.learningExperiences)
        )


        return root.toString(4)

    }





    private fun serializeMetadata(
        metadata: BackupMetadata
    ): JSONObject {


        return JSONObject().apply {

            put("backupId", metadata.backupId)

            put("createdAt", metadata.createdAt)

            put("appVersion", metadata.appVersion)

            put("databaseVersion", metadata.databaseVersion)

            put("brainVersion", metadata.brainVersion)

            put("backupType", metadata.backupType)

        }

    }







    private fun serializeDecisionMemory(
        items: List<DecisionMemoryEntity>
    ): JSONArray {


        val array = JSONArray()


        items.forEach { item ->


            array.put(
                JSONObject().apply {


                    put("id", item.id)

                    put("symbol", item.symbol)

                    put("trend", item.trend)

                    put("rsi", item.rsi)

                    put("volatility", item.volatility)

                    put("action", item.action)

                    put("confidence", item.confidence)

                    put("approved", item.approved)

                    put("reward", item.reward)

                    put("timestamp", item.timestamp)

                }
            )

        }


        return array

    }








    private fun serializeDecisionPatterns(
        items: List<DecisionPatternEntity>
    ): JSONArray {


        val array = JSONArray()


        items.forEach { item ->


            array.put(

                JSONObject().apply {


                    put("id", item.id)

                    put("patternName", item.patternName)

                    put("marketRegime", item.marketRegime)

                    put("trendState", item.trendState)

                    put("volatilityState", item.volatilityState)

                    put("successfulCount", item.successfulCount)

                    put("failedCount", item.failedCount)

                    put("averageReward", item.averageReward)

                    put("confidenceScore", item.confidenceScore)

                    put("usageCount", item.usageCount)

                    put("brainVersion", item.brainVersion)

                    put("timestamp", item.timestamp)


                }

            )


        }


        return array

    }








    private fun serializeLearningExperiences(
        items: List<LearningExperienceEntity>
    ): JSONArray {


        val array = JSONArray()


        items.forEach { item ->


            array.put(

                JSONObject().apply {


                    put("id", item.id)

                    put("decision", item.decision)

                    put("confidence", item.confidence)

                    put("riskApproved", item.riskApproved)

                    put("positionSize", item.positionSize)

                    put("riskScore", item.riskScore)

                    put("symbol", item.symbol)

                    put("price", item.price)

                    put("timeframe", item.timeframe)

                    put("marketRegime", item.marketRegime)

                    put("trendState", item.trendState)

                    put("volatilityState", item.volatilityState)

                    put("rsi", item.rsi)

                    put("macd", item.macd)

                    put("movingAverage", item.movingAverage)

                    put("atr", item.atr)

                    put("supportLevel", item.supportLevel)

                    put("resistanceLevel", item.resistanceLevel)

                    put("reward", item.reward)

                    put("profitLoss", item.profitLoss)

                    put("success", item.success)

                    put("holdingTime", item.holdingTime)

                    put("drawdown", item.drawdown)

                    put("schemaVersion", item.schemaVersion)

                    put("brainVersion", item.brainVersion)

                    put("strategyVersion", item.strategyVersion)

                    put("featureVersion", item.featureVersion)

                    put("timestamp", item.timestamp)

                }

            )


        }


        return array

    }


}
