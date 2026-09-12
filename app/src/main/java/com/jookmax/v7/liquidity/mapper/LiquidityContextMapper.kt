package com.jookmax.v7.liquidity.mapper


import com.jookmax.v7.liquidity.calculator.LiquidityWeightCalculator
import com.jookmax.v7.liquidity.fusion.LiquidityBiasFusionEngine
import com.jookmax.v7.liquidity.model.LiquidityAnalysisResult
import com.jookmax.v7.liquidity.model.LiquidityBias
import com.jookmax.v7.liquidity.model.LiquidityContext
import com.jookmax.v7.liquidity.model.LiquidityLevel

import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class LiquidityContextMapper @Inject constructor(


    private val liquidityWeightCalculator: LiquidityWeightCalculator,


    private val liquidityBiasFusionEngine: LiquidityBiasFusionEngine


) {



    fun map(

        result: LiquidityAnalysisResult,

        liquidityLevels: List<LiquidityLevel> = emptyList()

    ): LiquidityContext {



        val events = result.events





        val hasSweep =

            events.contains(

                com.jookmax.v7.liquidity.model.LiquidityEvent.LIQUIDITY_SWEEP

            )





        val hasStopHunt =

            events.contains(

                com.jookmax.v7.liquidity.model.LiquidityEvent.STOP_HUNT

            )





        val hasFakeBreakout =

            events.contains(

                com.jookmax.v7.liquidity.model.LiquidityEvent.FAKE_BREAKOUT

            )





        val bias =

            determineBias(

                hasSweep,

                hasStopHunt,

                hasFakeBreakout

            )





        val rawLiquidityScore =

            liquidityWeightCalculator.calculate(

                events

            )





        val liquidityScore =

            liquidityBiasFusionEngine.fuse(

                liquidityScore = rawLiquidityScore,

                liquidityBias = bias

            )





        return LiquidityContext(


            liquidityScore = liquidityScore,


            events = events,


            liquidityLevels = liquidityLevels,


            hasSweep = hasSweep,


            hasStopHunt = hasStopHunt,


            hasFakeBreakout = hasFakeBreakout,


            liquidityBias = bias


        )

    }







    private fun determineBias(



        hasSweep: Boolean,


        hasStopHunt: Boolean,


        hasFakeBreakout: Boolean



    ): LiquidityBias {



        return when {



            hasSweep && hasStopHunt ->


                LiquidityBias.BULLISH





            hasFakeBreakout ->


                LiquidityBias.NEUTRAL





            else ->


                LiquidityBias.NEUTRAL



        }


    }


}