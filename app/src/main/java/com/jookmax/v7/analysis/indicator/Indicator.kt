package com.jookmax.v7.analysis.indicator


interface Indicator {


    fun calculate(
        values: List<Double>
    ): Double


}