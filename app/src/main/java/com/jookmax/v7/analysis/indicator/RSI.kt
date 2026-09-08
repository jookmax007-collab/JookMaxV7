package com.jookmax.v7.analysis.indicator


class RSI(

    private val period: Int = 14

) : Indicator {


    override fun calculate(
        values: List<Double>
    ): Double {


        if (values.size <= period) {
            return 50.0
        }


        val changes =
            values.zipWithNext()
                .map { (a, b) ->
                    b - a
                }


        val gains =
            changes
                .takeLast(period)
                .filter {
                    it > 0
                }


        val losses =
            changes
                .takeLast(period)
                .filter {
                    it < 0
                }
                .map {
                    kotlin.math.abs(it)
                }



        if (losses.isEmpty()) {
            return 100.0
        }



        val averageGain =
            gains.average()



        val averageLoss =
            losses.average()



        val rs =
            averageGain / averageLoss



        return 100 -
                (100 / (1 + rs))


    }


}