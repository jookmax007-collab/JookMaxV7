package com.jookmax.v7.brain.intelligence.memory


interface DecisionMemory {


    fun remember(
        pattern: DecisionPattern
    )


    fun findSimilar(
        pattern: DecisionPattern
    ): List<DecisionPattern>


    fun getAll():
            List<DecisionPattern>


    fun clear()

}
