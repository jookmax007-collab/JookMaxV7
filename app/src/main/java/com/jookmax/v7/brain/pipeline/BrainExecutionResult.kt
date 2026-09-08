package com.jookmax.v7.brain.pipeline


import com.jookmax.v7.brain.decision.DecisionResult
import com.jookmax.v7.brain.intelligence.IntelligenceDecision



/**
 * Result of one complete brain execution cycle.
 *
 * Contains:
 *
 * BrainContext
 * +
 * DecisionResult
 * +
 * IntelligenceDecision
 *
 * Used by:
 * - BrainManager
 * - Decision Analytics
 * - Learning system
 * - Intelligence layer
 */
data class BrainExecutionResult(


    val context: BrainContext,


    val decision: DecisionResult,


    val intelligenceDecision: IntelligenceDecision

)