package com.jookmax.v7.brain.intelligence.memory


import com.jookmax.v7.brain.context.MarketContext

import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class CurrentMarketPatternMapper @Inject constructor() {



    fun map(

        context: MarketContext

    ): CurrentMarketPattern {



        return CurrentMarketPattern(


            symbol = context.symbol,


            trend = context.trend,


            rsi = context.rsi,


            volatility = context.volatility,


            marketRegime = context.marketRegime,


            session = context.session


        )

    }


}