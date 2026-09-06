package com.jookmax.v7.engine.runtime


import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob



class EngineCoroutineScope {


    private val job = SupervisorJob()



    val scope = CoroutineScope(
        job + Dispatchers.Default
    )



    fun cancel(){

        job.cancel()

    }
}