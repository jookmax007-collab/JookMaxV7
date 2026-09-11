package com.jookmax.v7.brain.pipeline


import com.jookmax.v7.core.model.MarketCandle



interface BrainExecutor {


    suspend fun execute(

        candle: MarketCandle

    ): BrainExecutionResult


}