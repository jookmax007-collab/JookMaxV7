package com.jookmax.v7.data.mapper


import com.jookmax.v7.core.model.MarketTick
import com.jookmax.v7.core.model.Symbol
import com.jookmax.v7.data.remote.dto.TickDto


class TickMapper {


    fun mapToDomain(
        dto: TickDto
    ): MarketTick {


        return MarketTick(

            symbol = Symbol(
                code = dto.symbol
            ),

            price = dto.price,

            timestamp = dto.timestamp

        )

    }


}
