package com.jookmax.v7.data.mapper

import com.jookmax.v7.core.model.MarketPrice
import com.jookmax.v7.core.model.Symbol


class MarketMapper {


    fun mapToDomain(
        price: Double,
        symbol: String,
        timestamp: Long
    ): MarketPrice {


        return MarketPrice(

            symbol = Symbol(
                code = symbol
            ),

            price = price,

            timestamp = timestamp

        )
    }



    fun mapFromDomain(
        marketPrice: MarketPrice
    ): MarketPriceDto {


        return MarketPriceDto(

            symbol = marketPrice.symbol.code,

            price = marketPrice.price,

            timestamp = marketPrice.timestamp

        )
    }

}



data class MarketPriceDto(

    val symbol: String,

    val price: Double,

    val timestamp: Long

)