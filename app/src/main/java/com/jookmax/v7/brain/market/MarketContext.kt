package com.jookmax.v7.brain.market


import com.jookmax.v7.analysis.model.MarketAnalysis
import com.jookmax.v7.structure.model.MarketStructure


data class MarketContext(

    val analysis: MarketAnalysis,

    val structure: MarketStructure

)