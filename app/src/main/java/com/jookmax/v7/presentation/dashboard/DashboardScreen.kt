package com.jookmax.v7.presentation.dashboard


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.hilt.navigation.compose.hiltViewModel



@Composable
fun DashboardScreen(

    viewModel: DashboardViewModel = hiltViewModel()

) {


    val state by viewModel.state.collectAsState()



    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),

        horizontalAlignment = Alignment.CenterHorizontally,

        verticalArrangement = Arrangement.Center

    ) {



        Text(

            text = "JookMax AI Dashboard",

            style = MaterialTheme.typography.headlineMedium

        )



        Spacer(

            modifier = Modifier.height(24.dp)

        )



        DashboardCard(

            title = "Engine",

            value = state.engineStatus

        )



        DashboardCard(

            title = "Market",

            value = "${state.symbol}  ${state.price}"

        )



        DashboardCard(

            title = "AI Brain",

            value = "${state.brainDecision} (${state.confidence}%)"

        )



        DashboardCard(

            title = "Trend",

            value = state.marketTrend

        )



        DashboardCard(

            title = "Risk",

            value = state.riskStatus

        )



        DashboardCard(

            title = "Total Decisions",

            value = state.totalDecisions.toString()

        )



        DashboardCard(

            title = "Decision Split",

            value =
                "BUY ${state.buyDecisions} | SELL ${state.sellDecisions} | HOLD ${state.holdDecisions}"

        )



        DashboardCard(

            title = "Average Confidence",

            value =
                "${(state.averageDecisionConfidence * 100).toInt()}%"

        )


    }


}





@Composable
private fun DashboardCard(

    title: String,

    value: String

) {


    Card(

        modifier = Modifier

            .fillMaxWidth()

            .padding(vertical = 6.dp)

    ) {


        Column(

            modifier = Modifier.padding(16.dp)

        ) {


            Text(title)


            Text(

                text = value,

                style = MaterialTheme.typography.titleLarge

            )


        }


    }


}
