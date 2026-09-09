package com.jookmax.v7.presentation.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import androidx.hilt.navigation.compose.hiltViewModel

import com.jookmax.v7.presentation.market.ui.MarketScreen
import com.jookmax.v7.presentation.monitoring.ui.MonitoringScreen



@Composable
fun JookMaxNavHost() {


    val navController = rememberNavController()



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


    }


}
