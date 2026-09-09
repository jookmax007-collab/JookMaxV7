package com.jookmax.v7.domain.analytics


import com.jookmax.v7.core.event.DecisionEvent



/**
 * Repository contract for decision analytics.
 *
 * Domain layer depends only on this contract.
 * Data layer provides the implementation.
 */
interface DecisionAnalyticsRepository {



    suspend fun saveDecision(

        event: DecisionEvent.DecisionGenerated

    )



    suspend fun getDecisions():

            List<DecisionEvent.DecisionGenerated>



    suspend fun clearDecisions()



}
