package com.jookmax.v7.brain.pipeline


import com.jookmax.v7.brain.decision.DecisionResult
import com.jookmax.v7.brain.intelligence.IntelligenceDecision
import com.jookmax.v7.brain.intelligence.validation.ValidatedDecision



/**
 * Result of one complete brain execution cycle.
 *
 * Pipeline:
 *
 * Market Analysis
 *        |
 * Risk Evaluation
 *        |
 * Decision Engine
 *        |
 * Intelligence Engine
 *        |
 * Decision Validator
 *        |
 * Final Brain Result
 *
 */
data class BrainExecutionResult(


    val context: BrainContext,


    val decision: DecisionResult,


    val intelligenceDecision: IntelligenceDecision,


    val validatedDecision: ValidatedDecision


)
