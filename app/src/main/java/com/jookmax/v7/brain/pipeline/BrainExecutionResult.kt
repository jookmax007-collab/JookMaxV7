package com.jookmax.v7.brain.pipeline


import com.jookmax.v7.brain.decision.DecisionResult
import com.jookmax.v7.brain.intelligence.IntelligenceDecision



/**
 * Result of one complete brain execution cycle.
 *
 * Pipeline Output:
 *
 * Market Analysis
 *        |
 * Risk Evaluation
 *        |
 * Decision Engine
 *        |
 * Intelligence Engine
 *        |
 * Final Brain Result
 *
 *
 * Used by:
 *
 * - BrainManager
 * - Decision Analytics
 * - Learning System
 * - Intelligence Layer
 *
 */
data class BrainExecutionResult(


    /**
     * Complete brain context
     */
    val context: BrainContext,



    /**
     * Raw decision from DecisionEngine
     */
    val decision: DecisionResult,



    /**
     * Intelligence adjusted decision
     */
    val intelligenceDecision: IntelligenceDecision



)