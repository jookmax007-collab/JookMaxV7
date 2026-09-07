package com.jookmax.v7.data.mapper


import com.jookmax.v7.core.model.MarketPrice
import com.jookmax.v7.core.model.Symbol
import com.jookmax.v7.data.local.entity.MarketPriceEntity



class MarketEntityMapper {



    fun mapToDomain(
        entity: MarketPriceEntity
    ): MarketPrice {


        return MarketPrice(

            symbol = Symbol(
                code = entity.symbol
            ),

            price = entity.price,

            timestamp = entity.timestamp,

            bid = entity.bid,

            ask = entity.ask

        )

    }




    fun mapToEntity(
        domain: MarketPrice
    ): MarketPriceEntity {


        return MarketPriceEntity(

            symbol = domain.symbol.code,

            price = domain.price,

            timestamp = domain.timestamp,

            bid = domain.bid,

            ask = domain.ask

        )

    }


}