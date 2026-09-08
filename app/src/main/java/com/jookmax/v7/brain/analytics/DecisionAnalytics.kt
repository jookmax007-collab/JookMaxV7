package com.jookmax.v7.brain.analytics


import com.jookmax.v7.brain.decision.DecisionAction
import javax.inject.Inject
import javax.inject.Singleton



/**
 * Provides analytics on generated trading decisions.
 *
 * Used for:
 * - Decision statistics
 * - Future learning system
 * - Performance evaluation
 */
@Singleton
class DecisionAnalytics @Inject constructor() {



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


}