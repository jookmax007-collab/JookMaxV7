package com.jookmax.v7.presentation.monitoring.ui


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import com.jookmax.v7.presentation.monitoring.MonitoringAction
import com.jookmax.v7.presentation.monitoring.MonitoringUiState
import com.jookmax.v7.presentation.monitoring.MonitoringViewModel



@Composable
fun MonitoringScreen(

    viewModel: MonitoringViewModel

) {


    val state =

        viewModel.state.collectAsState()



    MonitoringContent(

        state = state.value,

        onAction = viewModel::onAction

    )


}







@Composable
private fun MonitoringContent(

    state: MonitoringUiState,

    onAction: (MonitoringAction) -> Unit

) {


    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),

        verticalArrangement = Arrangement.Center,

        horizontalAlignment = Alignment.CenterHorizontally

    ) {


        when (state) {



            MonitoringUiState.Loading -> {


                CircularProgressIndicator()


            }







            is MonitoringUiState.Available -> {


                val report = state.report



                Text(

                    text = "JookMax Monitoring",

                    style = MaterialTheme.typography.headlineSmall

                )



                Text(

                    text = "Engine State: ${report.currentEngineState}"

                )



                Text(

                    text = "Processed Events: ${report.totalProcessedEvents}"

                )



                Text(

                    text = "Failed Events: ${report.totalFailedEvents}"

                )



                Text(

                    text = "Failure Rate: ${report.failureRate}"

                )



                Text(

                    text = "Average Latency: ${report.averageLatencyMs} ms"

                )



                Text(

                    text = "Total Decisions: ${report.totalDecisions}"

                )



                Text(

                    text = "BUY: ${report.buyDecisions}"

                )



                Text(

                    text = "SELL: ${report.sellDecisions}"

                )



                Text(

                    text = "HOLD: ${report.holdDecisions}"

                )



                Text(

                    text = "Decision Confidence: ${report.averageDecisionConfidence}"

                )





                Button(

                    onClick = {

                        onAction(

                            MonitoringAction.Refresh

                        )

                    }

                ) {


                    Text(

                        text = "Refresh"

                    )


                }


            }







            is MonitoringUiState.Error -> {



                Text(

                    text = state.message,

                    color = MaterialTheme.colorScheme.error

                )



                Button(

                    onClick = {


                        onAction(

                            MonitoringAction.Refresh

                        )


                    }

                ) {


                    Text(

                        text = "Retry"

                    )


                }


            }


        }


    }


}