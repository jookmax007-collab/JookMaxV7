package com.jookmax.v7.brain.reward.analytics


import com.jookmax.v7.brain.decision.DecisionAction



data class RewardAnalyticsResult(


    val totalExperiences: Int,


    val successfulTrades: Int,


    val failedTrades: Int,


    val averageReward: Double,


    val totalReward: Double,


    val winRate: Double,


    val bestAction: DecisionAction?,


    val worstAction: DecisionAction?


) {


    companion object {


        fun empty(): RewardAnalyticsResult {


            return RewardAnalyticsResult(

                totalExperiences = 0,

                successfulTrades = 0,

                failedTrades = 0,

                averageReward = 0.0,

                totalReward = 0.0,

                winRate = 0.0,

                bestAction = null,

                worstAction = null

            )

        }

    }

}