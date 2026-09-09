package com.jookmax.v7.brain.risk


/**
 * User risk configuration.
 *
 * Defines account and exposure limits
 * used by Risk Engine.
 */
data class RiskProfile(

    val accountBalance: Double = 10000.0,

    val riskPercent: Double = 1.0,

    val maxExposure: Double = 5.0

)
