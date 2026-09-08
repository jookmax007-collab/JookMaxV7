package com.jookmax.v7.analysis.engine


import com.jookmax.v7.analysis.model.MarketAnalysis
import com.jookmax.v7.core.model.MarketCandle

import javax.inject.Inject
import javax.inject.Singleton



/**
 * Main analysis pipeline.
 *
 * Flow:
 *
 * MarketCandle
 *
 *      |
 *      v
 *
 * Analyzer
 *
 *      |
 *      v
 *
 * MarketAnalysis
 *
 */
@Singleton
class AnalysisEngine @Inject constructor(


    private val technicalAnalyzer: TechnicalAnalyzer


) {



    fun analyze(

        candles: List<MarketCandle>

    ): MarketAnalysis {


        return technicalAnalyzer.analyze(

            candles

        )


    }


}