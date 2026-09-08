package com.jookmax.v7


import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import com.jookmax.v7.engine.manager.EngineManager
import com.jookmax.v7.presentation.navigation.JookMaxNavHost
import com.jookmax.v7.ui.theme.JookMaxV7Theme

import dagger.hilt.android.AndroidEntryPoint

import javax.inject.Inject



@AndroidEntryPoint
class MainActivity : ComponentActivity() {



    @Inject
    lateinit var engineManager: EngineManager




    override fun onCreate(
        savedInstanceState: Bundle?
    ) {

        super.onCreate(savedInstanceState)


        enableEdgeToEdge()



        engineManager.startEngine()



        setContent {


            JookMaxV7Theme {


                JookMaxNavHost()


            }


        }

    }




    override fun onDestroy() {


        engineManager.stopEngine()


        super.onDestroy()

    }


}