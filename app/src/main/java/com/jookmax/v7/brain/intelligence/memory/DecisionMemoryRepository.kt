package com.jookmax.v7.brain.intelligence.memory


import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class DecisionMemoryRepository @Inject constructor(


    private val memory: DecisionMemory


) {



    fun save(
        pattern: DecisionPattern
    ) {


        memory.remember(pattern)

    }





    fun search(
        pattern: DecisionPattern
    ):
            List<DecisionPattern> {


        return memory.findSimilar(pattern)

    }





    fun history():

            List<DecisionPattern> {


        return memory.getAll()

    }





    fun clear(){

        memory.clear()

    }


}