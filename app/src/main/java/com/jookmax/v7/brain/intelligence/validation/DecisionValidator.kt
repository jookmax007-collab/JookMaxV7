package com.jookmax.v7.brain.intelligence.validation

import com.jookmax.v7.brain.intelligence.IntelligenceDecision


interface DecisionValidator {

    fun validate(
        decision: IntelligenceDecision
    ): ValidatedDecision

}
