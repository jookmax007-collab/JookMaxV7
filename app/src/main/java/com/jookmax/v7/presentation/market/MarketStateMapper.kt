package com.jookmax.v7.presentation.market


import com.jookmax.v7.core.model.MarketHistory
import com.jookmax.v7.core.model.MarketPrice

import javax.inject.Inject
import javax.inject.Singleton



/**
 * Maps market domain models to presentation UI states.
 *
 * Responsibility:
 * - Keep ViewModel clean
 * - Convert domain data into UI representation
 *
 * Architecture:
 *
 * Domain
 *   |
 *   v
 * MarketPrice + MarketHistory
 *   |
 *   v
 * MarketStateMapper
 *   |
 *   v
 * MarketUiState
 */
@Singleton
class MarketStateMapper @Inject constructor() {



    fun map(

        price: MarketPrice?,

        history: MarketHistory?

    ): MarketUiState {


        return MarketUiState.Success(

            price = price,

            history = history

        )


    }







    fun mapError(

        throwable: Throwable

    ): MarketUiState {


        return MarketUiState.Error(

            message =
                throwable.message
                    ?: "Unknown market error"

        )


    }



}