package com.jookmax.v7.liquidity.detector


import com.jookmax.v7.core.model.MarketCandle
import com.jookmax.v7.structure.detector.SwingDetector
import com.jookmax.v7.structure.model.SwingType
import com.jookmax.v7.liquidity.model.LiquidityDirection
import com.jookmax.v7.liquidity.model.LiquidityLevel

import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class LiquidityLevelDetector @Inject constructor(

    private val swingDetector: SwingDetector

) {



    fun detect(

        candles: List<MarketCandle>

    ): List<LiquidityLevel> {



        if (candles.isEmpty())

            return emptyList()



        val swings =

            swingDetector.detect(

                candles

            )



        return swings.map { swing ->



            when(swing.type) {



                SwingType.HIGH ->

                    LiquidityLevel(

                        price = swing.price,

                        direction = LiquidityDirection.BUY_SIDE,

                        strength = 0.7,

                        timestamp = swing.candle.timestamp

                    )



                SwingType.LOW ->

                    LiquidityLevel(

                        price = swing.price,

                        direction = LiquidityDirection.SELL_SIDE,

                        strength = 0.7,

                        timestamp = swing.candle.timestamp

                    )

            }

        }

    }

}