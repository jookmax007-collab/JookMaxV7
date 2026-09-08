package com.jookmax.v7


import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import com.jookmax.v7.presentation.navigation.JookMaxNavHost
import com.jookmax.v7.ui.theme.JookMaxV7Theme

import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(
        savedInstanceState: Bundle?
    ) {

        super.onCreate(savedInstanceState)


        enableEdgeToEdge()



        setContent {


            JookMaxV7Theme {


                JookMaxNavHost()


            }

        }

    }

}