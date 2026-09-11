package com.jookmax.v7.brain.context


import com.jookmax.v7.analysis.model.MarketAnalysis
import com.jookmax.v7.structure.model.MarketStructure

import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class MarketContextMapper @Inject constructor() {


    fun map(

        analysis: MarketAnalysis,

        structure: MarketStructure,

        price: Double = 0.0

    ): MarketContext {


        return MarketContext(


            symbol = analysis.symbol.code,


            price = price,


            trend = analysis.trend,


            rsi = analysis.rsi,


            volatility = analysis.volatility,



            marketRegime = determineRegime(
                analysis
            ),



            session = "UNKNOWN",



            dxy = 0.0,


            yield = 0.0,


            newsRisk = 0.0,



            structureDirection =
                structure.direction.name,



            bosDetected =
                structure.bosDetected,



            chochDetected =
                structure.chochDetected,



            lastSwingHigh =
                structure.lastSwingHigh?.price,



            lastSwingLow =
                structure.lastSwingLow?.price


        )

    }




    private fun determineRegime(

        analysis: MarketAnalysis

    ): String {


        return when {


            analysis.volatility > 0.02 ->

                "HIGH_VOLATILITY"



            analysis.trend == "BULLISH" ->

                "TREND_UP"



            analysis.trend == "BEARISH" ->

                "TREND_DOWN"



            else ->

                "RANGE"


        }

    }


}