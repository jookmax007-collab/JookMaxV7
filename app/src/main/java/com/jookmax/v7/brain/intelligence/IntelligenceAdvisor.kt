package com.jookmax.v7.brain.intelligence

import com.jookmax.v7.brain.confidence.ConfidenceAnalytics
import com.jookmax.v7.brain.confidence.ConfidenceFeedbackManager
import com.jookmax.v7.brain.learning.LearningAnalytics
import com.jookmax.v7.brain.learning.LearningPatternAnalyzer
import com.jookmax.v7.brain.intelligence.feedback.IntelligenceFeedbackAnalyzer

import javax.inject.Inject
import javax.inject.Singleton


/**
 * Intelligence Advisor
 *
 * Combines:
 *
 * Confidence Intelligence
 * Learning Intelligence
 * Learning Pattern Intelligence
 * Feedback Intelligence
 *
 * Output:
 *
 * Final Decision Adjustment Factor
 */
@Singleton
class IntelligenceAdvisor @Inject constructor(

    private val confidenceAnalytics: ConfidenceAnalytics,

    private val confidenceFeedbackManager: ConfidenceFeedbackManager,

    private val learningAnalytics: LearningAnalytics,

    private val learningPatternAnalyzer: LearningPatternAnalyzer,

    private val feedbackAnalyzer: IntelligenceFeedbackAnalyzer

) {

    suspend fun calculateAdjustment(): Double {

        val confidenceAdjustment =
            confidenceAnalytics.calculateAdjustment(
                confidenceFeedbackManager.getHistory()
            )

        val learningPerformance =
            learningAnalytics.calculatePerformanceScore()

        val learningAdjustment =
            when {

                learningPerformance > 0.75 ->
                    1.05

                learningPerformance < 0.35 ->
                    0.95

                else ->
                    1.0
            }

        val patternAdjustment =
            learningPatternAnalyzer.calculateAdjustment()

        val feedbackAdjustment =
            feedbackAnalyzer.calculateAdjustment()

        return (
            confidenceAdjustment *
                learningAdjustment *
                patternAdjustment *
                feedbackAdjustment
            ).coerceIn(
                0.8,
                1.2
            )
    }
}
