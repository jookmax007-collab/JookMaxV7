package com.jookmax.v7.core.events


import com.jookmax.v7.brain.decision.DecisionResult
import com.jookmax.v7.core.model.Symbol



/**
 * Events related to trading decisions.
 *
 * Used for:
 * - Decision analytics
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
     * Emitted when DecisionEngine creates a decision.
     */
    data class DecisionGenerated(


        val symbol: Symbol,


        val decision: DecisionResult,


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