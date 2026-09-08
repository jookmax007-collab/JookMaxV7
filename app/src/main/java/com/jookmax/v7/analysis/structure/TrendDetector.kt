package com.jookmax.v7.analysis.structure



class TrendDetector {



    fun detect(
        values: List<Double>
    ): MarketStructure {


        if(values.size < 5) {

            return MarketStructure.SIDEWAYS

        }



        val recent =
            values.takeLast(5)



        val first =
            recent.first()



        val last =
            recent.last()



        val change =
            last - first



        return when {


            change > 0 ->
                MarketStructure.UPTREND



            change < 0 ->
                MarketStructure.DOWNTREND



            else ->
                MarketStructure.SIDEWAYS


        }


    }


}