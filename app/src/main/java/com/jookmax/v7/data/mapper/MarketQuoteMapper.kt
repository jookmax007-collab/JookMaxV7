package com.jookmax.v7.data.mapper


import com.jookmax.v7.core.model.MarketQuote
import com.jookmax.v7.core.model.Symbol
import com.jookmax.v7.data.remote.dto.MarketQuoteDto


class MarketQuoteMapper {


    fun mapToDomain(
        dto: MarketQuoteDto
    ): MarketQuote {


        return MarketQuote(

            symbol = Symbol(
                code = dto.symbol
            ),

            bid = dto.bid,

            ask = dto.ask,

            last = dto.bid,

            timestamp = dto.timestamp

        )

    }


}
