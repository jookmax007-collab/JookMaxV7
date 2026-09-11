package com.jookmax.v7.brain.backtest


import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class BacktestService @Inject constructor(


    private val backtestRunner: BacktestRunner


) {



    private val mutex = Mutex()



    private var lastResult: BacktestResult? = null





    suspend fun runBacktest(): BacktestResult {


        return mutex.withLock {


            val result =

                backtestRunner.run()



            lastResult = result



            result

        }


    }





    fun getLastResult(): BacktestResult? {


        return lastResult


    }





    fun clear() {


        lastResult = null


    }


}