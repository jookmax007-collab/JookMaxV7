package com.jookmax.v7.data.mapper


import com.jookmax.v7.core.model.MarketPrice
import com.jookmax.v7.core.model.Symbol



class MarketMapper {



    fun mapToDomain(
        dto: MarketPriceDto
    ): MarketPrice {


        return MarketPrice(

            symbol = Symbol(
                code = dto.symbol
            ),

            price = dto.price,

            timestamp = dto.timestamp

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
