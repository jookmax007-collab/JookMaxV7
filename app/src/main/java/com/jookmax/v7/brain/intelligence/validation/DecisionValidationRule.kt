package com.jookmax.v7.brain.intelligence.validation


interface DecisionValidationRule {


    fun validate(
        decision: ValidatedDecision
    ): Boolean


}