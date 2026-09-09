package com.jookmax.v7.brain.intelligence.validation


import com.jookmax.v7.brain.intelligence.IntelligenceDecision
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class IntelligenceDecisionValidator @Inject constructor() :
    DecisionValidator {


    override fun validate(
        decision: IntelligenceDecision
    ): ValidatedDecision {


        val reasons = mutableListOf<String>()


        var score = 1.0



        if (decision.confidence < 0.5) {

            score -= 0.4

            reasons.add(
                "Low confidence score"
            )

        }



        if (decision.reason.isBlank()) {

            score -= 0.3

            reasons.add(
                "Decision reason is empty"
            )

        }



        if (score < 0.0) {

            score = 0.0

        }



        val approved = score >= 0.5



        if (approved) {

            reasons.add(
                "Decision passed validation"
            )

        } else {

            reasons.add(
                "Decision rejected by validator"
            )

        }



        return ValidatedDecision(

            originalDecision = decision,

            approved = approved,

            finalAction = decision.action,

            validationScore = score,

            validationReasons = reasons

        )

    }

}