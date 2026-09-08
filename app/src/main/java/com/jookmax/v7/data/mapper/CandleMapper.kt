package com.jookmax.v7.data.mapper


import com.jookmax.v7.core.model.MarketCandle
import com.jookmax.v7.core.model.Symbol
import com.jookmax.v7.core.model.TimeFrame
import com.jookmax.v7.data.remote.dto.CandleDto


class CandleMapper {


    fun mapToDomain(
        dto: CandleDto
    ): MarketCandle {


        return MarketCandle(

            symbol = Symbol(
                code = dto.symbol
            ),

            timeFrame = TimeFrame.fromMinutes(
                dto.timeframe
            ),

            timestamp = dto.timestamp,

            open = dto.open,

            high = dto.high,

            low = dto.low,

            close = dto.close,

            volume = dto.volume ?: 0.0

        )

    }


}
