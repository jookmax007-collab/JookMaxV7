package com.jookmax.v7.brain.pipeline


import com.jookmax.v7.brain.decision.DecisionResult



/**
 * Result of one complete brain execution cycle.
 *
 * Contains:
 *
 * BrainContext
 * +
 * Final DecisionResult
 *
 * Used by:
 * - BrainManager
 * - Decision Analytics
 * - Learning system
 */
data class BrainExecutionResult(


    val context: BrainContext,


    val decision: DecisionResult


)