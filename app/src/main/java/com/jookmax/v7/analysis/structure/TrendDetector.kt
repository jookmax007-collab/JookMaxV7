package com.jookmax.v7.analysis.structure


import com.jookmax.v7.core.model.MarketCandle

import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class TrendDetector @Inject constructor() {


    fun detect(
        candles: List<MarketCandle>
    ): MarketStructure {


        if (candles.size < 5) {

            return MarketStructure.UNKNOWN

        }


        val recent =
            candles.takeLast(5)



        val first =
            recent.first().close


        val last =
            recent.last().close



        return when {


            last > first ->

                MarketStructure.UPTREND



            last < first ->

                MarketStructure.DOWNTREND



            else ->

                MarketStructure.RANGE

        }

    }


}
