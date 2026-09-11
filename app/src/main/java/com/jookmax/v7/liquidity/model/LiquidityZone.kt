package com.jookmax.v7.liquidity.model


data class LiquidityZone(


    val type: LiquidityType,


    val highPrice: Double,


    val lowPrice: Double,


    val strength: Double,


    val touched: Boolean,


    val swept: Boolean,


    val timestamp: Long


)