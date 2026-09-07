package com.jookmax.v7.monitoring


import javax.inject.Inject
import javax.inject.Singleton



/**
 * Observes JookMax engine runtime changes.
 *
 * Responsible for:
 * - Runtime state observation
 * - Connecting runtime data with monitoring layer
 * - Preparing future analytics integration
 */
@Singleton
class RuntimeObserver @Inject constructor() {



    private var lastState: String =
        "UNKNOWN"




    private var lastUpdateTime: Long =
        0L





    fun observe(

        state: String

    ) {


        lastState = state


        lastUpdateTime =
            System.currentTimeMillis()


    }







    fun getCurrentState(): String {


        return lastState


    }







    fun getLastUpdateTime(): Long {


        return lastUpdateTime


    }







    fun reset() {


        lastState =
            "UNKNOWN"


        lastUpdateTime =
            0L


    }



}
