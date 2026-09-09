package com.jookmax.v7.data.mapper


import com.jookmax.v7.core.model.MarketPrice
import com.jookmax.v7.core.model.Symbol
import com.jookmax.v7.data.remote.dto.MarketPriceDto



class MarketRemoteMapper {



    fun mapToDomain(

        dto: MarketPriceDto

    ): MarketPrice {


        return MarketPrice(


            symbol = Symbol(

                code = dto.symbol

            ),


            price = dto.price,


            timestamp = dto.timestamp,


            bid = dto.bid,


            ask = dto.ask


        )


    }


}
