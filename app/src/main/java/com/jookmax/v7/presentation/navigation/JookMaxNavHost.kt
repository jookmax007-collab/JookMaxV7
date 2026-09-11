package com.jookmax.v7.presentation.navigation


import androidx.compose.runtime.Composable

import androidx.hilt.navigation.compose.hiltViewModel

import androidx.navigation.NavHostController

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


import com.jookmax.v7.presentation.market.ui.MarketScreen
import com.jookmax.v7.presentation.monitoring.ui.MonitoringScreen
import com.jookmax.v7.presentation.backtest.BacktestScreen



@Composable
fun JookMaxNavHost(

    navController: NavHostController

) {



    NavHost(

        navController = navController,

        startDestination = ScreenRoute.Market.route

    ) {



        composable(

            route = ScreenRoute.Market.route

        ) {


            MarketScreen(

                viewModel = hiltViewModel()

            )


        }





        composable(

            route = ScreenRoute.Monitoring.route

        ) {


            MonitoringScreen(

                viewModel = hiltViewModel()

            )


        }






        composable(

            route = ScreenRoute.Backtest.route

        ) {


            BacktestScreen(

                viewModel = hiltViewModel()

            )


        }



    }


}