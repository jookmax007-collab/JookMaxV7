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



/**
 * ViewModel bridge for market presentation layer.
 *
 * Responsibilities:
 * - Execute market use cases
 * - Handle UI actions
 * - Manage UI state
 *
 * Architecture:
 *
 * UI
 *  |
 *  v
 * MarketAction
 *  |
 *  v
 * MarketViewModel
 *  |
 *  v
 * MarketStateMapper
 *  |
 *  v
 * MarketUiState
 */
@HiltViewModel
class MarketViewModel @Inject constructor(


    private val getMarketPriceUseCase: GetMarketPriceUseCase,


    private val getMarketHistoryUseCase: GetMarketHistoryUseCase,


    private val clearMarketCacheUseCase: ClearMarketCacheUseCase,


    private val marketStateMapper: MarketStateMapper


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


        when (action) {


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

                    marketStateMapper.map(

                        price = price,

                        history = history

                    )


            } catch (exception: Exception) {



                _state.value =

                    marketStateMapper.mapError(

                        exception

                    )


            }


        }


    }








    private fun clearCache() {


        viewModelScope.launch {


            try {


                clearMarketCacheUseCase()



                refresh()



            } catch (exception: Exception) {



                _state.value =

                    marketStateMapper.mapError(

                        exception

                    )


            }


        }


    }



}