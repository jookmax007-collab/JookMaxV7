package com.jookmax.v7.brain.reward

import com.jookmax.v7.brain.learning.LearningBrain
import com.jookmax.v7.brain.learning.LearningExperience
import com.jookmax.v7.brain.reward.model.RewardExperience
import com.jookmax.v7.brain.trading.model.TradeOutcome
import com.jookmax.v7.domain.repository.RewardExperienceRepository

import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class RewardEngine @Inject constructor(

    private val rewardCalculator: RewardCalculator,

    private val learningBrain: LearningBrain,

    private val rewardExperienceRepository: RewardExperienceRepository

) {


    suspend fun evaluate(

        outcome: TradeOutcome

    ): RewardResult {


        val result = rewardCalculator.calculate(

            outcome

        )


        learningBrain.learn(

            success = result.success,

            reward = result.reward

        )


        saveRewardExperience(

            outcome,

            result

        )


        saveLearningExperience(

            outcome,

            result

        )


        return result
    }



    private suspend fun saveRewardExperience(

        outcome: TradeOutcome,

        result: RewardResult

    ) {


        val experience = RewardExperience(

            action = outcome.action,

            entryPrice = outcome.entryPrice,

            exitPrice = outcome.exitPrice,

            reward = result.reward,

            success = result.success,

            reason = result.reason,

            confidence =
            outcome.learningContext?.confidence ?: 0.0,

            positionSize =
            outcome.positionSize,

            stopLoss =
            outcome.stopLoss,

            takeProfit =
            outcome.takeProfit,

            symbol =
            outcome.learningContext?.symbol ?: "UNKNOWN",

            trend =
            outcome.learningContext?.trend ?: "UNKNOWN",

            rsi =
            outcome.learningContext?.rsi ?: 0.0,

            movingAverage =
            outcome.learningContext?.movingAverage ?: 0.0,

            volatility =
            outcome.learningContext?.volatility ?: 0.0

        )


        rewardExperienceRepository.saveExperience(

            experience

        )
    }




    private fun saveLearningExperience(

        outcome: TradeOutcome,

        result: RewardResult

    ) {


        val context = outcome.learningContext

            ?: return



        val experience = LearningExperience(

            decision = context.action,

            confidence = context.confidence,

            riskApproved = context.positionSize > 0.0,

            positionSize = context.positionSize,

            stopLoss = context.stopLoss,

            takeProfit = context.takeProfit,

            symbol = context.symbol,

            trend = context.trend,

            rsi = context.rsi,

            movingAverage = context.movingAverage,

            volatility = context.volatility,

            reward = result.reward,

            success = result.success

        )


        learningBrain.addExperience(

            experience

        )
    }
}




