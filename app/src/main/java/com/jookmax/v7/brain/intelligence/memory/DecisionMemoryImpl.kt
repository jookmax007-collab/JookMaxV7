package com.jookmax.v7.brain.intelligence.memory


import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class DecisionMemoryImpl @Inject constructor() :
    DecisionMemory {



    private val memory =
        mutableListOf<DecisionPattern>()





    override fun remember(
        pattern: DecisionPattern
    ) {


        memory.add(pattern)

    }





    override fun findSimilar(
        pattern: DecisionPattern
    ): List<DecisionPattern> {


        return memory.filter {


            it.action == pattern.action &&
                    kotlin.math.abs(
                        it.confidence - pattern.confidence
                    ) < 0.2


        }


    }





    override fun getAll():

            List<DecisionPattern> {


        return memory.toList()

    }





    override fun clear() {


        memory.clear()

    }


}