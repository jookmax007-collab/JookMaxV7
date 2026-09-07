package com.jookmax.v7.core.monitoring


import javax.inject.Inject
import javax.inject.Singleton



/**
 * Stores performance snapshot history.
 *
 * Responsible for:
 * - Keeping recent performance snapshots
 * - Providing historical runtime analytics data
 * - Preparing future chart and analytics systems
 */
@Singleton
class MetricsHistory @Inject constructor() {



    private val snapshots =
        mutableListOf<PerformanceSnapshot>()



    private val maxHistorySize =
        100





    fun addSnapshot(

        snapshot: PerformanceSnapshot

    ) {


        snapshots.add(

            snapshot

        )



        if (snapshots.size > maxHistorySize) {


            snapshots.removeAt(0)


        }


    }







    fun getHistory(): List<PerformanceSnapshot> {


        return snapshots.toList()


    }







    fun getLatest(): PerformanceSnapshot? {


        return snapshots.lastOrNull()


    }







    fun size(): Int {


        return snapshots.size


    }







    fun clear() {


        snapshots.clear()


    }



}