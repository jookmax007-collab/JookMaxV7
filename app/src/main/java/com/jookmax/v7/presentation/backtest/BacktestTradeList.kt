package com.jookmax.v7.presentation.backtest


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import com.jookmax.v7.brain.backtest.model.BacktestTrade



@Composable
fun BacktestTradeList(

    trades: List<BacktestTrade>

) {


    Column(

        modifier = Modifier
            .fillMaxWidth()

    ) {


        Text(

            text = "Trades",

            style = MaterialTheme.typography.titleMedium

        )



        trades.forEach { trade ->


            Card(

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)

            ) {


                Column(

                    modifier = Modifier.padding(12.dp)

                ) {


                    Text(

                        text = "Action: ${trade.action}"

                    )


                    Text(

                        text = "Entry: ${trade.entryPrice}"

                    )


                    Text(

                        text = "Exit: ${trade.exitPrice ?: "-"}"

                    )


                    Text(

                        text = "Profit: ${trade.profitLoss}"

                    )


                }


            }


        }


    }


}