package com.jookmax.v7.analysis.indicator



class MovingAverage(

    private val period: Int = 20

) : Indicator {


    override fun calculate(
        values: List<Double>
    ): Double {


        if(values.isEmpty()) {
            return 0.0
        }


        val data =
            values.takeLast(period)


        return data.average()

    }


}
