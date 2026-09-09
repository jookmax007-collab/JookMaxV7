package com.jookmax.v7.brain.confidence


import com.jookmax.v7.brain.risk.RiskDecision

import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class RiskConfidenceCalculator @Inject constructor(){



    fun calculate(

        riskDecision: RiskDecision

    ): Double {



        return if (

            riskDecision.positionSize > 0.0

        ) {


            1.0


        } else {


            0.0


        }

    }

}
