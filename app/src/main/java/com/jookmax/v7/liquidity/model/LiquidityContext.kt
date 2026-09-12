package com.jookmax.v7.liquidity.model


data class LiquidityContext(


    val liquidityScore: Double = 0.0,


    val events: List<LiquidityEvent> = emptyList(),


    val liquidityLevels: List<LiquidityLevel> = emptyList(),


    val hasSweep: Boolean = false,


    val hasStopHunt: Boolean = false,


    val hasFakeBreakout: Boolean = false,


    val liquidityBias: LiquidityBias = LiquidityBias.NEUTRAL,


    val timestamp: Long = System.currentTimeMillis()


)



enum class LiquidityBias {


    BULLISH,


    BEARISH,


    NEUTRAL

}