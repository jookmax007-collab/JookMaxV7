package com.jookmax.v7.data.backup


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

            serializeMetadata(
                snapshot.metadata
            )

        )



        root.put(

            "decisionMemory",

            serializeDecisionMemory(
                snapshot.decisionMemory
            )

        )



        root.put(

            "decisionPatterns",

            serializeDecisionPatterns(
                snapshot.decisionPatterns
            )

        )



        root.put(

            "learningExperiences",

            serializeLearningExperiences(
                snapshot.learningExperiences
            )

        )



        return root.toString(4)

    }





    private fun serializeMetadata(

        metadata: BackupMetadata

    ): JSONObject {


        return JSONObject().apply {


            put(
                "backupId",
                metadata.backupId
            )


            put(
                "createdAt",
                metadata.createdAt
            )


            put(
                "appVersion",
                metadata.appVersion
            )


            put(
                "databaseVersion",
                metadata.databaseVersion
            )


            put(
                "brainVersion",
                metadata.brainVersion
            )


            put(
                "backupType",
                metadata.backupType
            )

        }

    }







    private fun serializeDecisionMemory(

        items: List<com.jookmax.v7.data.local.entity.DecisionMemoryEntity>

    ): JSONArray {


        val array = JSONArray()


        items.forEach {


            array.put(

                JSONObject().apply {


                    put(
                        "id",
                        it.id
                    )


                    put(
                        "symbol",
                        it.symbol
                    )


                    put(
                        "trend",
                        it.trend
                    )


                    put(
                        "rsi",
                        it.rsi
                    )


                    put(
                        "volatility",
                        it.volatility
                    )


                    put(
                        "action",
                        it.action
                    )


                    put(
                        "confidence",
                        it.confidence
                    )


                    put(
                        "approved",
                        it.approved
                    )


                    put(
                        "reward",
                        it.reward
                    )


                    put(
                        "timestamp",
                        it.timestamp
                    )

                }

            )

        }


        return array

    }







    private fun serializeDecisionPatterns(

        items: List<com.jookmax.v7.data.local.entity.DecisionPatternEntity>

    ): JSONArray {


        val array = JSONArray()


        items.forEach {


            array.put(

                JSONObject().apply {


                    put(
                        "id",
                        it.id
                    )


                    put(
                        "patternName",
                        it.patternName
                    )


                    put(
                        "marketRegime",
                        it.marketRegime
                    )


                    put(
                        "confidenceScore",
                        it.confidenceScore
                    )


                    put(
                        "timestamp",
                        it.timestamp
                    )

                }

            )

        }


        return array

    }







    private fun serializeLearningExperiences(

        items: List<com.jookmax.v7.data.local.entity.LearningExperienceEntity>

    ): JSONArray {


        val array = JSONArray()


        items.forEach {


            array.put(

                JSONObject().apply {


                    put(
                        "id",
                        it.id
                    )


                    put(
                        "decision",
                        it.decision
                    )


                    put(
                        "symbol",
                        it.symbol
                    )


                    put(
                        "reward",
                        it.reward
                    )


                    put(
                        "success",
                        it.success
                    )


                    put(
                        "timestamp",
                        it.timestamp
                    )

                }

            )

        }


        return array

    }



}
