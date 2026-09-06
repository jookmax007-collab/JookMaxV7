package com.jookmax.v7.brain.risk


class RiskBrain {


    private var riskLevel: RiskLevel = RiskLevel.LOW



    fun evaluateRisk(
        marketVolatility: Double
    ): RiskResult {


        riskLevel = when {

            marketVolatility >= 0.8 ->
                RiskLevel.HIGH


            marketVolatility >= 0.5 ->
                RiskLevel.MEDIUM


            else ->
                RiskLevel.LOW
        }



        return RiskResult(
            level = riskLevel,
            allowed = riskLevel != RiskLevel.HIGH
        )
    }



    fun getCurrentRiskLevel(): RiskLevel {

        return riskLevel

    }



    fun reset() {

        riskLevel = RiskLevel.LOW

    }

}




enum class RiskLevel {

    LOW,

    MEDIUM,

    HIGH

}




data class RiskResult(

    val level: RiskLevel,

    val allowed: Boolean

)