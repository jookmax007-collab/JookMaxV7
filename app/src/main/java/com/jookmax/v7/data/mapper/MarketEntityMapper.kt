package com.jookmax.v7.data.mapper


import com.jookmax.v7.core.model.MarketCandle
import com.jookmax.v7.core.model.MarketPrice
import com.jookmax.v7.core.model.Symbol
import com.jookmax.v7.core.model.TimeFrame

import com.jookmax.v7.data.local.entity.MarketCandleEntity
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





    fun mapCandleToEntity(
        candle: MarketCandle
    ): MarketCandleEntity {


        return MarketCandleEntity(

            id = candle.timestamp,

            symbol = candle.symbol.code,

            timeframe = mapTimeFrameToMinutes(
                candle.timeFrame
            ),

            timestamp = candle.timestamp,

            open = candle.open,

            high = candle.high,

            low = candle.low,

            close = candle.close,

            volume = candle.volume

        )

    }





    fun mapCandleToDomain(
        entity: MarketCandleEntity
    ): MarketCandle {


        return MarketCandle(

            symbol = Symbol(
                code = entity.symbol
            ),

            timeFrame = mapMinutesToTimeFrame(
                entity.timeframe
            ),

            timestamp = entity.timestamp,

            open = entity.open,

            high = entity.high,

            low = entity.low,

            close = entity.close,

            volume = entity.volume ?: 0.0

        )

    }





    private fun mapTimeFrameToMinutes(
        timeFrame: TimeFrame
    ): Int {


        return when(timeFrame) {

            TimeFrame.M1 -> 1

            TimeFrame.M5 -> 5

            TimeFrame.M15 -> 15

            TimeFrame.H1 -> 60

            TimeFrame.H4 -> 240

            TimeFrame.D1 -> 1440

            TimeFrame.W1 -> 10080

        }

    }





    private fun mapMinutesToTimeFrame(
        minutes: Int
    ): TimeFrame {


        return when(minutes) {

            1 -> TimeFrame.M1

            5 -> TimeFrame.M5

            15 -> TimeFrame.M15

            60 -> TimeFrame.H1

            240 -> TimeFrame.H4

            1440 -> TimeFrame.D1

            10080 -> TimeFrame.W1


            else -> TimeFrame.H1

        }

    }


}
