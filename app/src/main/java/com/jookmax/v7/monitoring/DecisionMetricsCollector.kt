package com.jookmax.v7.monitoring


import javax.inject.Inject
import javax.inject.Singleton



/**
 * Collects decision related analytics.
 *
 * Responsible for:
 * - BUY count
 * - SELL count
 * - HOLD count
 * - Average confidence
 *
 * Separate from runtime metrics.
 */
@Singleton
class DecisionMetricsCollector @Inject constructor() {



    private var totalDecisions = 0L


    private var buyDecisions = 0L


    private var sellDecisions = 0L


    private var holdDecisions = 0L


    private var totalConfidence = 0.0





    fun recordDecision(

        action: String,

        confidence: Double

    ) {


        totalDecisions++


        totalConfidence += confidence



        when(action) {


            "BUY" -> {

                buyDecisions++

            }



            "SELL" -> {

                sellDecisions++

            }



            "HOLD" -> {

                holdDecisions++

            }

        }


    }





    fun getTotalDecisions(): Long {


        return totalDecisions

    }





    fun getBuyDecisions(): Long {


        return buyDecisions

    }





    fun getSellDecisions(): Long {


        return sellDecisions

    }





    fun getHoldDecisions(): Long {


        return holdDecisions

    }





    fun getAverageConfidence(): Double {


        if(totalDecisions == 0L) {

            return 0.0

        }


        return totalConfidence / totalDecisions

    }





    fun reset() {


        totalDecisions = 0

        buyDecisions = 0

        sellDecisions = 0

        holdDecisions = 0

        totalConfidence = 0.0

    }


}