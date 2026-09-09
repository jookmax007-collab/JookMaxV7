package com.jookmax.v7.brain.intelligence.validation


import com.jookmax.v7.brain.decision.DecisionAction
import com.jookmax.v7.brain.intelligence.IntelligenceDecision



class IntelligenceDecisionValidator : DecisionValidator {


    override fun validate(

        decision: IntelligenceDecision

    ): ValidatedDecision {


        val reasons = mutableListOf<String>()



        val validationScore =

            decision.confidence





        if (decision.confidence < 0.5) {


            reasons.add(

                "Confidence below validation threshold"

            )



            return ValidatedDecision(

                originalDecision = decision,

                approved = false,

                finalAction = DecisionAction.HOLD,

                validationScore = validationScore,

                validationReasons = reasons

            )

        }







        reasons.add(

            "Decision passed intelligence validation"

        )





        return ValidatedDecision(

            originalDecision = decision,

            approved = true,

            finalAction = decision.action,

            validationScore = validationScore,

            validationReasons = reasons

        )

    }


}