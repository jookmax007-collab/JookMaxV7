package com.jookmax.v7.presentation.backtest


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.jookmax.v7.brain.backtest.BacktestResult
import com.jookmax.v7.brain.backtest.BacktestService

import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

import javax.inject.Inject



@HiltViewModel
class BacktestViewModel @Inject constructor(


    private val backtestService: BacktestService


) : ViewModel() {



    private val _state =

        MutableStateFlow<BacktestState>(

            BacktestState.Idle

        )



    val state: StateFlow<BacktestState> =

        _state.asStateFlow()





    fun runBacktest() {


        viewModelScope.launch {


            _state.value =

                BacktestState.Loading





            try {


                val result =

                    backtestService.runBacktest()



                _state.value =

                    BacktestState.Success(

                        result

                    )



            } catch (exception: Exception) {


                _state.value =

                    BacktestState.Error(

                        exception.message
                            ?: "Unknown error"

                    )


            }


        }


    }







    fun loadLastResult() {


        val result =

            backtestService.getLastResult()



        if(result != null) {


            _state.value =

                BacktestState.Success(

                    result

                )


        }


    }



}







sealed class BacktestState {


    data object Idle :

        BacktestState()



    data object Loading :

        BacktestState()



    data class Success(

        val result: BacktestResult

    ) : BacktestState()



    data class Error(

        val message: String

    ) : BacktestState()


}