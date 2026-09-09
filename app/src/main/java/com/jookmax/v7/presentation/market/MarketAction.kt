package com.jookmax.v7.presentation.market


sealed interface MarketAction {


    data object Refresh : MarketAction


    data object ClearCache : MarketAction


}
