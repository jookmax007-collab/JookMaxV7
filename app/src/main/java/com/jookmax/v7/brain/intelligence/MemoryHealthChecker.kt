package com.jookmax.v7.brain.intelligence

import com.jookmax.v7.domain.repository.PersistentDecisionMemoryRepository
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MemoryHealthChecker @Inject constructor(
    private val repository: PersistentDecisionMemoryRepository
) {


    suspend fun analyze(): MemoryHealthReport {

        val memories = repository.getAll()

        if (memories.isEmpty()) {

            return MemoryHealthReport(
                memorySize = 0,
                averageReward = 0.0,
                successRate = 0.0,
                averageConfidence = 0.0,
                qualityScore = 0.0
            )
        }


        val memorySize = memories.size


        val averageReward =
            memories
                .map { it.reward }
                .average()


        val successRate =
            memories
                .count { it.reward > 0.0 }
                .toDouble() /
                    memorySize.toDouble()


        val averageConfidence =
            memories
                .map { it.confidence }
                .average()


        val qualityScore =
            calculateQuality(
                successRate,
                averageConfidence,
                averageReward
            )


        return MemoryHealthReport(
            memorySize = memorySize,
            averageReward = averageReward,
            successRate = successRate,
            averageConfidence = averageConfidence,
            qualityScore = qualityScore
        )
    }


    private fun calculateQuality(
        successRate: Double,
        confidence: Double,
        reward: Double
    ): Double {


        val normalizedReward =
            when {
                reward <= 0.0 -> 0.0
                reward >= 1.0 -> 1.0
                else -> reward
            }


        return (
            successRate * 0.5 +
            confidence * 0.3 +
            normalizedReward * 0.2
        ).coerceIn(0.0, 1.0)

    }

}


data class MemoryHealthReport(

    val memorySize: Int,

    val averageReward: Double,

    val successRate: Double,

    val averageConfidence: Double,

    val qualityScore: Double

)