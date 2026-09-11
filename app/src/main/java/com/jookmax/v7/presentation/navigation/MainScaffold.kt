package com.jookmax.v7.presentation.navigation


import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue

import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController



@Composable
fun MainScaffold() {


    val navController = rememberNavController()



    val items = listOf(

        ScreenRoute.Dashboard,

        ScreenRoute.Market,

        ScreenRoute.Monitoring,

        ScreenRoute.Backtest

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
                                        "🏠"


                                    ScreenRoute.Market ->
                                        "📈"


                                    ScreenRoute.Monitoring ->
                                        "🖥"


                                    ScreenRoute.Backtest ->
                                        "🧪"


                                    else ->
                                        ""

                                }

                            )


                        },



                        label = {


                            Text(

                                text = when(screen) {


                                    ScreenRoute.Dashboard ->
                                        "Dashboard"


                                    ScreenRoute.Market ->
                                        "Market"


                                    ScreenRoute.Monitoring ->
                                        "Monitor"


                                    ScreenRoute.Backtest ->
                                        "Backtest"


                                    else ->
                                        ""

                                }

                            )


                        }


                    )


                }


            }


        }


    ) {



        JookMaxNavHost(

            navController = navController

        )


    }


}