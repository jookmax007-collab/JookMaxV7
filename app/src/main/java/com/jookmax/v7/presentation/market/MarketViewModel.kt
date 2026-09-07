package com.jookmax.v7.presentation.market


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.jookmax.v7.domain.usecase.ClearMarketCacheUseCase
import com.jookmax.v7.domain.usecase.GetMarketHistoryUseCase
import com.jookmax.v7.domain.usecase.GetMarketPriceUseCase

import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

import kotlinx.coroutines.launch

import javax.inject.Inject



@HiltViewModel
class MarketViewModel @Inject constructor(


    private val getMarketPriceUseCase: GetMarketPriceUseCase,


    private val getMarketHistoryUseCase: GetMarketHistoryUseCase,


    private val clearMarketCacheUseCase: ClearMarketCacheUseCase


) : ViewModel() {



    private val _state =

        MutableStateFlow<MarketUiState>(

            MarketUiState.Loading

        )



    val state: StateFlow<MarketUiState>
        get() = _state.asStateFlow()





    fun onAction(

        action: MarketAction

    ) {


        when(action) {


            MarketAction.Refresh -> {

                refresh()

            }



            MarketAction.ClearCache -> {

                clearCache()

            }


        }


    }







    private fun refresh() {


        viewModelScope.launch {


            _state.value =

                MarketUiState.Loading



            try {


                val price =

                    getMarketPriceUseCase()



                val history =

                    getMarketHistoryUseCase()



                _state.value =

                    MarketUiState.Success(

                        price = price,

                        history = history

                    )


            } catch(exception: Exception) {


                _state.value =

                    MarketUiState.Error(

                        exception.message
                            ?: "Unknown error"

                    )


            }


        }


    }







    private fun clearCache() {


        viewModelScope.launch {


            clearMarketCacheUseCase()



            refresh()


        }


    }


}