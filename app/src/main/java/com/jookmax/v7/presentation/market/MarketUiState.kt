package com.jookmax.v7.presentation.market


import com.jookmax.v7.core.model.MarketHistory
import com.jookmax.v7.core.model.MarketPrice



sealed interface MarketUiState {


    data object Loading : MarketUiState



    data class Success(

        val price: MarketPrice?,

        val history: MarketHistory?

    ) : MarketUiState



    data class Error(

        val message: String

    ) : MarketUiState


}