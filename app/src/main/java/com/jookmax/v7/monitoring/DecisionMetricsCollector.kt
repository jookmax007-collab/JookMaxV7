package com.jookmax.v7.monitoring


import javax.inject.Inject
import javax.inject.Singleton



/**
 * Collects decision related analytics.
 *
 * Responsible for:
 *
 * Decision Metrics:
 * - BUY count
 * - SELL count
 * - HOLD count
 * - Average confidence
 *
 * Validation Metrics:
 * - Approved decisions
 * - Rejected decisions
 * - Validation score
 * - Validation success rate
 *
 */
@Singleton
class DecisionMetricsCollector @Inject constructor() {



    private var totalDecisions = 0L

    private var buyDecisions = 0L

    private var sellDecisions = 0L

    private var holdDecisions = 0L

    private var totalConfidence = 0.0



    // Validation Metrics


    private var approvedDecisions = 0L

    private var rejectedDecisions = 0L

    private var totalValidationScore = 0.0







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








    fun recordValidation(

        approved: Boolean,

        validationScore: Double

    ) {


        totalValidationScore += validationScore



        if(approved) {

            approvedDecisions++

        }

        else {

            rejectedDecisions++

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









    fun getApprovedDecisions(): Long {

        return approvedDecisions

    }






    fun getRejectedDecisions(): Long {

        return rejectedDecisions

    }







    fun getValidationSuccessRate(): Double {


        val total =

            approvedDecisions + rejectedDecisions



        if(total == 0L) {

            return 0.0

        }



        return approvedDecisions.toDouble() /

                total.toDouble()

    }







    fun getAverageValidationScore(): Double {


        val total =

            approvedDecisions + rejectedDecisions



        if(total == 0L) {

            return 0.0

        }



        return totalValidationScore / total

    }









    fun reset() {


        totalDecisions = 0

        buyDecisions = 0

        sellDecisions = 0

        holdDecisions = 0

        totalConfidence = 0.0


        approvedDecisions = 0

        rejectedDecisions = 0

        totalValidationScore = 0.0

    }


}