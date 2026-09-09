package com.jookmax.v7.brain.analytics


import com.jookmax.v7.brain.decision.DecisionAction
import com.jookmax.v7.brain.intelligence.validation.ValidatedDecision

import javax.inject.Inject
import javax.inject.Singleton



/**
 * Decision Analytics
 *
 * Responsible for:
 *
 * - Decision statistics
 * - Intelligence validation metrics
 * - Performance evaluation
 * - Future learning feedback
 *
 */
@Singleton
class DecisionAnalytics @Inject constructor() {



    // =========================
    // Decision Statistics
    // =========================



    fun countBuyDecisions(

        records: List<DecisionRecord>

    ): Int {


        return records.count {

            it.action == DecisionAction.BUY.name

        }

    }







    fun countSellDecisions(

        records: List<DecisionRecord>

    ): Int {


        return records.count {

            it.action == DecisionAction.SELL.name

        }

    }







    fun countHoldDecisions(

        records: List<DecisionRecord>

    ): Int {


        return records.count {

            it.action == DecisionAction.HOLD.name

        }

    }







    fun averageConfidence(

        records: List<DecisionRecord>

    ): Double {


        if (records.isEmpty()) {

            return 0.0

        }



        return records

            .map {

                it.confidence

            }

            .average()

    }






    // =========================
    // Validation Analytics
    // =========================



    /**
     * Number of approved intelligence decisions
     */
    fun countValidatedDecisions(

        decisions: List<ValidatedDecision>

    ): Int {


        return decisions.count {

            it.approved

        }

    }







    /**
     * Number of rejected intelligence decisions
     */
    fun countRejectedDecisions(

        decisions: List<ValidatedDecision>

    ): Int {


        return decisions.count {

            !it.approved

        }

    }







    /**
     * Validator approval rate
     */
    fun validationSuccessRate(

        decisions: List<ValidatedDecision>

    ): Double {


        if (decisions.isEmpty()) {

            return 0.0

        }



        return (

                decisions.count {

                    it.approved

                }.toDouble()

                /

                decisions.size.toDouble()

                )

    }







    /**
     * Average validation confidence score
     */
    fun averageValidationScore(

        decisions: List<ValidatedDecision>

    ): Double {


        if (decisions.isEmpty()) {

            return 0.0

        }



        return decisions

            .map {

                it.validationScore

            }

            .average()

    }


}
