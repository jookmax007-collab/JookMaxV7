package com.jookmax.v7.brain.intelligence


import com.jookmax.v7.brain.decision.DecisionAction



data class IntelligenceDecision(


    val action: DecisionAction,


    val confidence: Double,


    val reason: String,


    val timestamp: Long = System.currentTimeMillis()


)