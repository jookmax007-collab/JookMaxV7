package com.jookmax.v7.brain.pipeline


import com.jookmax.v7.core.model.MarketCandle

import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class DefaultBrainExecutor @Inject constructor(

    private val brainPipeline: BrainPipeline

) : BrainExecutor {



    override fun execute(

        candle: MarketCandle

    ): BrainExecutionResult {


        return brainPipeline.execute(

            candle

        )

    }


}