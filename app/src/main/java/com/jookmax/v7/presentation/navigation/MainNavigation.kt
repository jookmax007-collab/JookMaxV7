package com.jookmax.v7.presentation.navigation


import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable

import androidx.navigation.NavController



@Composable
fun MainNavigationBar(

    navController: NavController

) {


    NavigationBar {



        NavigationBarItem(


            selected = false,


            onClick = {


                navController.navigate(
                    ScreenRoute.Market.route
                )


            },


            icon = {


                Text(
                    "📈"
                )


            },


            label = {


                Text(
                    "Market"
                )


            }


        )







        NavigationBarItem(


            selected = false,


            onClick = {


                navController.navigate(
                    ScreenRoute.Monitoring.route
                )


            },


            icon = {


                Text(
                    "🖥"
                )


            },


            label = {


                Text(
                    "Monitor"
                )


            }


        )







        NavigationBarItem(


            selected = false,


            onClick = {


                navController.navigate(
                    ScreenRoute.Backtest.route
                )


            },


            icon = {


                Text(
                    "🧪"
                )


            },


            label = {


                Text(
                    "Backtest"
                )


            }


        )



    }


}