package com.jookmax.v7.presentation.market


/**
 * Contract for Market screen presentation layer.
 *
 * Defines communication between:
 *
 * UI
 *  |
 *  v
 * MarketViewModel
 */
interface MarketScreenContract {


    /**
     * Handle user actions from UI.
     */
    fun onAction(
        action: MarketAction
    )

}
