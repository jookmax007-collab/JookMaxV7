package com.jookmax.v7.monitoring


import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

import javax.inject.Inject
import javax.inject.Singleton



/**
 * Stores performance snapshot history.
 *
 * Responsible for:
 * - Keeping recent performance snapshots
 * - Providing historical runtime analytics data
 * - Publishing reactive history updates
 * - Preparing future chart and analytics systems
 */
@Singleton
class MetricsHistory @Inject constructor() {



    private val snapshots =
        mutableListOf<PerformanceSnapshot>()



    private val maxHistorySize =
        100





    private val _historyFlow =

        MutableStateFlow<List<PerformanceSnapshot>>(

            emptyList()

        )





    /**
     * Reactive stream of snapshot history.
     *
     * Future usage:
     * - Live monitoring dashboard
     * - Charts
     * - Runtime analytics
     */
    val historyFlow:

            StateFlow<List<PerformanceSnapshot>>

        get() = _historyFlow.asStateFlow()







    fun addSnapshot(

        snapshot: PerformanceSnapshot

    ) {


        snapshots.add(

            snapshot

        )



        if (snapshots.size > maxHistorySize) {


            snapshots.removeAt(0)


        }





        _historyFlow.value =

            snapshots.toList()



    }









    fun getHistory():

            List<PerformanceSnapshot> {


        return snapshots.toList()


    }









    fun getLatest():

            PerformanceSnapshot? {


        return snapshots.lastOrNull()


    }









    fun size():

            Int {


        return snapshots.size


    }









    fun clear() {


        snapshots.clear()



        _historyFlow.value =

            emptyList()



    }



}
