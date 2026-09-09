package com.jookmax.v7.presentation.market.ui


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

import com.jookmax.v7.presentation.market.MarketAction
import com.jookmax.v7.presentation.market.MarketUiState
import com.jookmax.v7.presentation.market.MarketViewModel



/**
 * Market screen entry point.
 *
 * UI Layer only:
 *
 * UI
 *  |
 *  v
 * MarketViewModel
 *  |
 *  v
 * UseCases
 *
 */
@Composable
fun MarketScreen(

    viewModel: MarketViewModel

) {


    val state =

        viewModel.state.collectAsState()



    MarketContent(

        state = state.value,

        onAction = viewModel::onAction

    )


}





@Composable
private fun MarketContent(

    state: MarketUiState,

    onAction: (MarketAction) -> Unit

) {



    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),

        verticalArrangement = Arrangement.Center,

        horizontalAlignment = Alignment.CenterHorizontally

    ) {


        when (state) {



            MarketUiState.Loading -> {


                CircularProgressIndicator()


            }





            is MarketUiState.Success -> {



                Text(

                    text = "JookMax Market",

                    style = MaterialTheme.typography.headlineSmall

                )



                Text(

                    text =

                        state.price?.price?.toString()

                            ?: "No market price available"


                )





                Button(

                    onClick = {

                        onAction(

                            MarketAction.Refresh

                        )

                    }

                ) {


                    Text(

                        text = "Refresh"

                    )


                }


            }





            is MarketUiState.Error -> {



                Text(

                    text = state.message,

                    color = MaterialTheme.colorScheme.error

                )





                Button(

                    onClick = {


                        onAction(

                            MarketAction.Refresh

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
