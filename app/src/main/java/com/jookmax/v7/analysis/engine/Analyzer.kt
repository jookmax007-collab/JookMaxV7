package com.jookmax.v7.analysis.engine


import com.jookmax.v7.core.model.MarketCandle
import com.jookmax.v7.analysis.model.MarketAnalysis



/**
 * Base contract for market analyzers.
 */
interface Analyzer {


    fun analyze(
        candles: List<MarketCandle>
    ): MarketAnalysis


}