package com.jookmax.v7.brain.learning

import com.jookmax.v7.brain.decision.DecisionAction
import com.jookmax.v7.domain.repository.LearningRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Test

class LearningPatternAnalyzerTest {

    @Test
    fun learningPatternScoreShouldCalculateFromExperiences() = runBlocking {

        val repository = FakeLearningRepository()

        repository.experiences.add(
            LearningExperience(
                decision = DecisionAction.BUY,
                confidence = 0.9,
                riskApproved = true,
                positionSize = 1.0,
                stopLoss = 2490.0,
                takeProfit = 2520.0,
                symbol = "XAUUSD",
                trend = "UP",
                rsi = 60.0,
                movingAverage = 2500.0,
                volatility = 10.0,
                reward = 0.8,
                success = true
            )
        )

        val analyzer = LearningPatternAnalyzer(
            repository
        )

        val score = analyzer.calculatePatternScore()

        assertTrue(
            score > 0.5
        )
    }
}

class FakeLearningRepository : LearningRepository {

    val experiences =
        mutableListOf<LearningExperience>()

    override suspend fun saveExperience(
        experience: LearningExperience
    ) {
        experiences.add(experience)
    }

    override suspend fun getExperiences():
            List<LearningExperience> {

        return experiences
    }

    override suspend fun getLatestExperience():
            LearningExperience? {

        return experiences.lastOrNull()
    }

    override suspend fun getExperienceCount():
            Int {

        return experiences.size
    }

    override suspend fun getAverageReward():
            Double {

        if (experiences.isEmpty()) {
            return 0.0
        }

        return experiences
            .map { it.reward }
            .average()
    }

    override suspend fun getSuccessRate():
            Double {

        if (experiences.isEmpty()) {
            return 0.0
        }

        return experiences
            .count { it.success }
            .toDouble() /
                experiences.size.toDouble()
    }

    override suspend fun clearMemory() {
        experiences.clear()
    }
}
