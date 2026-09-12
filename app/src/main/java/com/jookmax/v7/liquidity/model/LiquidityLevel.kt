package com.jookmax.v7.liquidity.model


data class LiquidityLevel(

    val price: Double,

    val direction: LiquidityDirection,

    val strength: Double,

    val timestamp: Long

)