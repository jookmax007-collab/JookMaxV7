package com.jookmax.v7.core.events


import com.jookmax.v7.brain.decision.DecisionResult
import com.jookmax.v7.brain.intelligence.IntelligenceDecision
import com.jookmax.v7.brain.intelligence.validation.ValidatedDecision
import com.jookmax.v7.core.model.Symbol



/**
 * Events related to trading decisions.
 *
 * Used for:
 * - Decision analytics
 * - Intelligence monitoring
 * - Future learning system
 * - Decision history tracking
 */
sealed class DecisionEvent(

    override val type: EventType = EventType.ENGINE,

    override val metadata: EventMetadata = EventMetadata(

        source = "Decision",

        timestamp = System.currentTimeMillis()

    )

) : EngineEvent {



    /**
     * Emitted when Brain Pipeline creates final decision.
     *
     * Contains:
     *
     * DecisionEngine Output
     * +
     * Intelligence Layer Output
     * +
     * Validation Result
     */
    data class DecisionGenerated(


        val symbol: Symbol,


        /**
         * Raw decision from DecisionEngine.
         */
        val decision: DecisionResult,



        /**
         * Intelligence adjusted decision.
         */
        val intelligenceDecision: IntelligenceDecision,



        /**
         * Final validation result.
         */
        val validatedDecision: ValidatedDecision,



        /**
         * Market confidence score used for decision.
         */
        val marketScore: Double,



        /**
         * Risk permission state.
         */
        val riskAllowed: Boolean,



        /**
         * Learning reward used during decision.
         */
        val learningReward: Double,



        val timestamp: Long = System.currentTimeMillis()


    ) : DecisionEvent()



}