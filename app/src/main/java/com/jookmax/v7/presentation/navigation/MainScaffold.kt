package com.jookmax.v7.presentation.navigation


import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue

import androidx.hilt.navigation.compose.hiltViewModel

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController


import com.jookmax.v7.presentation.dashboard.DashboardScreen
import com.jookmax.v7.presentation.market.ui.MarketScreen
import com.jookmax.v7.presentation.monitoring.ui.MonitoringScreen
import com.jookmax.v7.presentation.backtest.BacktestScreen



@Composable
fun MainScaffold() {


    val navController = rememberNavController()



    val items = listOf(

        ScreenRoute.Dashboard,

        ScreenRoute.Market,

        ScreenRoute.Monitoring,

        ScreenRoute.Risk,

        ScreenRoute.Backtest,

        ScreenRoute.Settings

    )





    Scaffold(


        bottomBar = {


            NavigationBar {


                val backStackEntry by

                    navController
                        .currentBackStackEntryAsState()



                val currentRoute =

                    backStackEntry
                        ?.destination
                        ?.route





                items.forEach { screen ->



                    NavigationBarItem(


                        selected =

                            currentRoute == screen.route,



                        onClick = {


                            navController.navigate(

                                screen.route

                            ) {


                                launchSingleTop = true

                                restoreState = true

                            }


                        },



                        icon = {


                            Text(

                                text = when(screen) {


                                    ScreenRoute.Dashboard ->
                                        "D"


                                    ScreenRoute.Market ->
                                        "M"


                                    ScreenRoute.Monitoring ->
                                        "O"


                                    ScreenRoute.Risk ->
                                        "R"


                                    ScreenRoute.Backtest ->
                                        "B"


                                    ScreenRoute.Settings ->
                                        "S"

                                }

                            )


                        },



                        label = {


                            Text(

                                screen.route

                            )


                        }



                    )


                }



            }



        }



    ) { padding ->





        NavHost(


            navController = navController,


            startDestination = ScreenRoute.Dashboard.route



        ) {





            composable(

                ScreenRoute.Dashboard.route

            ) {


                DashboardScreen()


            }






            composable(

                ScreenRoute.Market.route

            ) {


                MarketScreen(

                    viewModel = hiltViewModel()

                )


            }






            composable(

                ScreenRoute.Monitoring.route

            ) {


                MonitoringScreen(

                    viewModel = hiltViewModel()

                )


            }






            composable(

                ScreenRoute.Backtest.route

            ) {


                BacktestScreen()


            }






            composable(

                ScreenRoute.Risk.route

            ) {


                Text(

                    text = "Risk Screen"

                )


            }






            composable(

                ScreenRoute.Settings.route

            ) {


                Text(

                    text = "Settings Screen"

                )


            }



        }



    }



}