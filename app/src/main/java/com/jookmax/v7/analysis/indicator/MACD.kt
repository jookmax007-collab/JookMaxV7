package com.jookmax.v7.analysis.indicator



class MACD : Indicator {


    private val fast =
        MovingAverage(12)


    private val slow =
        MovingAverage(26)



    override fun calculate(
        values: List<Double>
    ): Double {


        return fast.calculate(values) -
                slow.calculate(values)


    }


}
