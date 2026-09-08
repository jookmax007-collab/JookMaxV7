package com.jookmax.v7.analysis.indicator


class ATR(

    private val period: Int = 14

) : Indicator {


    override fun calculate(
        values: List<Double>
    ): Double {


        if(values.size < 2) {
            return 0.0
        }


        val ranges =
            values.zipWithNext()
                .map { (a,b) ->
                    kotlin.math.abs(
                        b - a
                    )
                }
                .takeLast(period)



        return ranges.average()


    }


}