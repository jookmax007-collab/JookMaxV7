package com.jookmax.v7.brain.risk


import javax.inject.Inject
import javax.inject.Singleton



/**
 * Controls total market exposure.
 *
 * Risk Gate before decision execution.
 */
@Singleton
class ExposureManager @Inject constructor(

) {



    private var state = ExposureState()



    fun checkExposure(

        requestedExposure: Double

    ): Boolean {


        return (

            state.usedExposure + requestedExposure

        ) <= state.maxExposure


    }




    fun updateExposure(

        exposure: Double

    ) {


        state = state.copy(

            usedExposure = exposure

        )


    }




    fun getState(): ExposureState {


        return state


    }




    fun reset() {


        state = ExposureState()


    }



}
