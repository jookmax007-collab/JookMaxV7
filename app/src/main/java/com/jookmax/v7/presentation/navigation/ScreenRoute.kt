package com.jookmax.v7.presentation.navigation


sealed class ScreenRoute(
    val route: String
) {


    data object Market :
        ScreenRoute("market")


    data object Monitoring :
        ScreenRoute("monitoring")


    data object Dashboard :
        ScreenRoute("dashboard")


    data object Risk :
        ScreenRoute("risk")


    data object Backtest :
        ScreenRoute("backtest")


    data object Settings :
        ScreenRoute("settings")

}