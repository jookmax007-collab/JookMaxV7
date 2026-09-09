package com.jookmax.v7.brain.intelligence.memory

import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.abs


@Singleton
class PatternMatcher @Inject constructor() {


    fun match(
        current: DecisionPattern,
        history: List<DecisionPattern>
    ): List<DecisionPattern> {


        return history.filter {


            it.action == current.action &&

                    abs(
                        it.confidence - current.confidence
                    ) < 0.15


        }

    }

}