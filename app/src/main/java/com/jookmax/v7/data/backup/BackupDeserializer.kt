package com.jookmax.v7.data.backup


import com.jookmax.v7.data.local.entity.DecisionMemoryEntity
import com.jookmax.v7.data.local.entity.DecisionPatternEntity
import com.jookmax.v7.data.local.entity.LearningExperienceEntity

import org.json.JSONArray
import org.json.JSONObject

import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class BackupDeserializer @Inject constructor() {



    fun deserialize(

        json: String

    ): BackupSnapshot {


        val root = JSONObject(json)



        val metadataJson =
            root.getJSONObject("metadata")



        val metadata = BackupMetadata(

            backupId =
                metadataJson.getString("backupId"),

            createdAt =
                metadataJson.getLong("createdAt"),

            appVersion =
                metadataJson.getString("appVersion"),

            databaseVersion =
                metadataJson.getInt("databaseVersion"),

            brainVersion =
                metadataJson.getString("brainVersion"),

            backupType =
                metadataJson.optString(
                    "backupType",
                    "LOCAL"
                )

        )



        return BackupSnapshot(

            metadata = metadata,

            decisionMemory =
                deserializeDecisionMemory(
                    root.optJSONArray("decisionMemory")
                ),

            decisionPatterns =
                deserializeDecisionPatterns(
                    root.optJSONArray("decisionPatterns")
                ),

            learningExperiences =
                deserializeLearningExperiences(
                    root.optJSONArray("learningExperiences")
                )

        )

    }







    private fun deserializeDecisionMemory(

        array: JSONArray?

    ): List<DecisionMemoryEntity> {


        if (array == null) {
            return emptyList()
        }


        val result = mutableListOf<DecisionMemoryEntity>()


        for (i in 0 until array.length()) {


            val item = array.getJSONObject(i)


            result.add(

                DecisionMemoryEntity(

                    id =
                        item.optLong("id"),

                    symbol =
                        item.getString("symbol"),

                    trend =
                        item.getString("trend"),

                    rsi =
                        item.getDouble("rsi"),

                    volatility =
                        item.getDouble("volatility"),

                    action =
                        item.getString("action"),

                    confidence =
                        item.getDouble("confidence"),

                    approved =
                        item.getBoolean("approved"),

                    reward =
                        item.getDouble("reward"),

                    timestamp =
                        item.getLong("timestamp")

                )

            )

        }


        return result

    }









    private fun deserializeDecisionPatterns(

        array: JSONArray?

    ): List<DecisionPatternEntity> {


        if (array == null) {
            return emptyList()
        }


        val result = mutableListOf<DecisionPatternEntity>()


        for (i in 0 until array.length()) {


            val item = array.getJSONObject(i)



            result.add(

                DecisionPatternEntity(

                    id =
                        item.optLong("id"),

                    patternName =
                        item.getString("patternName"),

                    marketRegime =
                        item.getString("marketRegime"),

                    trendState =
                        item.optString(
                            "trendState"
                        ),

                    volatilityState =
                        item.optString(
                            "volatilityState"
                        ),

                    successfulCount =
                        item.optInt(
                            "successfulCount"
                        ),

                    failedCount =
                        item.optInt(
                            "failedCount"
                        ),

                    averageReward =
                        item.optDouble(
                            "averageReward"
                        ),

                    confidenceScore =
                        item.getDouble(
                            "confidenceScore"
                        ),

                    usageCount =
                        item.optLong(
                            "usageCount"
                        ),

                    brainVersion =
                        item.optString(
                            "brainVersion"
                        ),

                    timestamp =
                        item.getLong(
                            "timestamp"
                        )

                )

            )

        }


        return result

    }









    private fun deserializeLearningExperiences(

        array: JSONArray?

    ): List<LearningExperienceEntity> {


        if (array == null) {
            return emptyList()
        }


        val result = mutableListOf<LearningExperienceEntity>()


        for (i in 0 until array.length()) {


            val item = array.getJSONObject(i)


            result.add(

                LearningExperienceEntity(

                    id =
                        item.optLong("id"),

                    decision =
                        item.getString("decision"),

                    confidence =
                        item.optDouble("confidence"),

                    riskApproved =
                        item.optBoolean("riskApproved"),

                    positionSize =
                        item.optDouble("positionSize"),

                    riskScore =
                        item.optDouble("riskScore"),

                    symbol =
                        item.getString("symbol"),

                    price =
                        item.optDouble("price"),

                    timeframe =
                        item.optString("timeframe"),

                    marketRegime =
                        item.optString("marketRegime"),

                    trendState =
                        item.optString("trendState"),

                    volatilityState =
                        item.optString("volatilityState"),

                    rsi =
                        item.optDouble("rsi"),

                    macd =
                        item.optDouble("macd"),

                    movingAverage =
                        item.optDouble("movingAverage"),

                    atr =
                        item.optDouble("atr"),

                    supportLevel =
                        item.optDouble("supportLevel"),

                    resistanceLevel =
                        item.optDouble("resistanceLevel"),

                    reward =
                        item.optDouble("reward"),

                    profitLoss =
                        item.optDouble("profitLoss"),

                    success =
                        item.optBoolean("success"),

                    holdingTime =
                        item.optLong("holdingTime"),

                    drawdown =
                        item.optDouble("drawdown"),

                    schemaVersion =
                        item.optInt("schemaVersion"),

                    brainVersion =
                        item.optString("brainVersion"),

                    strategyVersion =
                        item.optString("strategyVersion"),

                    featureVersion =
                        item.optString("featureVersion"),

                    timestamp =
                        item.getLong("timestamp")

                )

            )

        }


        return result

    }


}
