package com.jookmax.v7.analysis.engine


import com.jookmax.v7.analysis.model.MarketAnalysis
import com.jookmax.v7.analysis.model.MarketAnalysisContext
import com.jookmax.v7.core.model.MarketCandle
import com.jookmax.v7.liquidity.engine.LiquidityEngine
import com.jookmax.v7.structure.MarketStructureEngine

import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class AnalysisEngine @Inject constructor(


    private val technicalAnalyzer: TechnicalAnalyzer,

    private val marketStructureEngine: MarketStructureEngine,

    private val liquidityEngine: LiquidityEngine


) {



    /**
     * Legacy technical analysis pipeline
     */
    fun analyze(

        candles: List<MarketCandle>

    ): MarketAnalysis {


        return technicalAnalyzer.analyze(

            candles

        )

    }





    /**
     * Full market intelligence pipeline
     *
     * Technical
     * +
     * Structure
     * +
     * Liquidity
     */
    fun analyzeContext(

        candles: List<MarketCandle>

    ): MarketAnalysisContext {



        val technical =

            technicalAnalyzer.analyze(

                candles

            )



        val structure =

            marketStructureEngine.analyze(

                candles

            )



        val liquidity =

            liquidityEngine.analyze(

                candles

            )



        return MarketAnalysisContext(

            technical = technical,

            structure = structure,

            liquidity = liquidity,

            timestamp = System.currentTimeMillis()

        )

    }

}