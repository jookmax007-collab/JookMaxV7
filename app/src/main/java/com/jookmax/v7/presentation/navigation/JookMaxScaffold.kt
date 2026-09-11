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
fun JookMaxScaffold() {



    val navController = rememberNavController()



    val items = listOf(

        ScreenRoute.Market,

        ScreenRoute.Monitoring,

        ScreenRoute.Backtest

    )






    Scaffold(



        bottomBar = {



            NavigationBar {



                val navBackStackEntry by

                    navController.currentBackStackEntryAsState()



                val currentRoute =

                    navBackStackEntry
                        ?.destination
                        ?.route






                items.forEach { item ->




                    NavigationBarItem(



                        selected =

                            currentRoute == item.route,





                        onClick = {



                            navController.navigate(

                                item.route

                            ) {



                                launchSingleTop = true

                                restoreState = true


                            }


                        },






                        icon = {



                            Text(

                                text = when(item) {


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

                                text = item.route

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