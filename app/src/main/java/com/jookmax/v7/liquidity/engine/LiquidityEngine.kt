package com.jookmax.v7.liquidity.engine


import com.jookmax.v7.core.model.MarketCandle
import com.jookmax.v7.liquidity.detector.LiquidityLevelDetector
import com.jookmax.v7.liquidity.analyzer.LiquidityAnalyzer
import com.jookmax.v7.liquidity.mapper.LiquidityContextMapper
import com.jookmax.v7.liquidity.model.LiquidityContext

import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class LiquidityEngine @Inject constructor(

    private val liquidityLevelDetector: LiquidityLevelDetector,

    private val liquidityAnalyzer: LiquidityAnalyzer,

    private val liquidityContextMapper: LiquidityContextMapper

) {



    fun analyze(

        candles: List<MarketCandle>

    ): LiquidityContext {


        if (candles.isEmpty()) {

            return LiquidityContext()

        }



        val liquidityLevels =

            liquidityLevelDetector.detect(

                candles

            )



        val mainLiquidityLevel =

            liquidityLevels

                .firstOrNull()

                ?.price

                ?: 0.0




        val analysisResult =

            liquidityAnalyzer.analyze(

                candles = candles,

                liquidityLevel = mainLiquidityLevel

            )




        return liquidityContextMapper.map(

            result = analysisResult,

            liquidityLevels = liquidityLevels

        )

    }

}