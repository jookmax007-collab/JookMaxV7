package com.jookmax.v7.structure.model

import com.jookmax.v7.core.model.MarketCandle


data class SwingPoint(

    val candle: MarketCandle,

    val price: Double,

    val type: SwingType

)


enum class SwingType {

    HIGH,

    LOW

}
