package com.jookmax.v7.brain.reward.analytics


import com.jookmax.v7.brain.decision.DecisionAction
import com.jookmax.v7.brain.reward.model.RewardExperience

import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class RewardAnalytics @Inject constructor() {



    fun analyze(

        experiences: List<RewardExperience>

    ): RewardAnalyticsResult {


        if (experiences.isEmpty()) {


            return RewardAnalyticsResult.empty()

        }





        val totalReward = experiences.sumOf {

            it.reward

        }




        val averageReward =

            totalReward /

                    experiences.size





        val successful = experiences.count {


            it.success

        }





        val failed = experiences.size - successful






        val winRate =

            successful.toDouble() /

                    experiences.size.toDouble()





        val bestAction =

            experiences

                .groupBy {

                    it.action

                }

                .maxByOrNull {

                    group ->

                    group.value.sumOf {

                        it.reward

                    }

                }

                ?.key






        val worstAction =

            experiences

                .groupBy {

                    it.action

                }

                .minByOrNull {

                    group ->

                    group.value.sumOf {

                        it.reward

                    }

                }

                ?.key






        return RewardAnalyticsResult(


            totalExperiences = experiences.size,


            successfulTrades = successful,


            failedTrades = failed,


            averageReward = averageReward,


            totalReward = totalReward,


            winRate = winRate,


            bestAction = bestAction,


            worstAction = worstAction


        )

    }

}