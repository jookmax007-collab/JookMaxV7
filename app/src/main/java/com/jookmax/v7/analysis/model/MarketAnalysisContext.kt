package com.jookmax.v7.analysis.model


import com.jookmax.v7.liquidity.model.LiquidityContext
import com.jookmax.v7.structure.model.MarketStructure


data class MarketAnalysisContext(

    val technical: MarketAnalysis,

    val structure: MarketStructure,

    val liquidity: LiquidityContext,

    val timestamp: Long

)