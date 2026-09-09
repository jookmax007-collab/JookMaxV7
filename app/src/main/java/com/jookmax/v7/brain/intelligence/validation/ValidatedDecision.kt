package com.jookmax.v7.brain.intelligence.validation

import com.jookmax.v7.brain.decision.DecisionAction
import com.jookmax.v7.brain.intelligence.IntelligenceDecision


data class ValidatedDecision(

    val originalDecision: IntelligenceDecision,

    val approved: Boolean,

    val finalAction: DecisionAction,

    val validationScore: Double,

    val validationReasons: List<String>,

    val timestamp: Long = System.currentTimeMillis()

)