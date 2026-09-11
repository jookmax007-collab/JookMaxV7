package com.jookmax.v7.presentation.backtest


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.hilt.navigation.compose.hiltViewModel



@Composable
fun BacktestScreen(

    viewModel: BacktestViewModel = hiltViewModel()

) {


    val state by viewModel.state.collectAsState()



    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),

        horizontalAlignment = Alignment.CenterHorizontally,

        verticalArrangement = Arrangement.Center

    ) {



        Text(

            text = "XAU/USD Intelligence Backtest",

            style = MaterialTheme.typography.headlineMedium

        )



        Spacer(

            modifier = Modifier.height(24.dp)

        )





        when(val currentState = state) {



            BacktestState.Idle -> {


                Button(

                    onClick = {

                        viewModel.runBacktest()

                    }

                ) {

                    Text("Run Backtest")

                }


            }






            BacktestState.Loading -> {


                CircularProgressIndicator()



                Spacer(

                    modifier = Modifier.height(16.dp)

                )



                Text(

                    "Brain Simulation Running..."

                )


            }







            is BacktestState.Success -> {


                val result = currentState.result



                Card(

                    modifier = Modifier
                        .fillMaxWidth()

                ) {


                    Column(

                        modifier = Modifier.padding(16.dp)

                    ) {


                        Text(

                            "Backtest Completed",

                            style = MaterialTheme.typography.titleLarge

                        )



                        Spacer(

                            modifier = Modifier.height(12.dp)

                        )



                        Text("Candles: ${result.totalCandles}")

                        Text("Trades: ${result.totalTrades}")

                        Text("Winning: ${result.winningTrades}")

                        Text("Losing: ${result.losingTrades}")



                        Spacer(

                            modifier = Modifier.height(12.dp)

                        )



                        Text(

                            "Win Rate: ${(result.winRate * 100).toInt()}%"

                        )



                        Text(

                            "Net Profit: ${result.netProfit}"

                        )





                        Spacer(

                            modifier = Modifier.height(12.dp)

                        )



                        Row(

                            modifier = Modifier.fillMaxWidth(),

                            horizontalArrangement = Arrangement.SpaceBetween

                        ) {


                            Text(

                                "BUY ${result.buySignals}"

                            )



                            Text(

                                "SELL ${result.sellSignals}"

                            )



                            Text(

                                "HOLD ${result.holdSignals}"

                            )


                        }





                        Spacer(

                            modifier = Modifier.height(16.dp)

                        )



                        Text(

                            "---- Advanced Metrics ----"

                        )



                        Text(

                            "Profit Factor: ${result.metrics.profitFactor}"

                        )



                        Text(

                            "Average Win: ${result.metrics.averageWin}"

                        )



                        Text(

                            "Average Loss: ${result.metrics.averageLoss}"

                        )



                        Text(

                            "Expectancy: ${result.metrics.expectancy}"

                        )



                        Text(

                            "Max Drawdown: ${result.metrics.maxDrawdown}"

                        )



                        Spacer(

                            modifier = Modifier.height(16.dp)

                        )



                        Button(

                            onClick = {

                                viewModel.runBacktest()

                            }

                        ) {


                            Text(

                                "Run Again"

                            )


                        }



                    }


                }


            }







            is BacktestState.Error -> {


                Text(

                    "Error: ${currentState.message}"

                )


            }


        }


    }


}