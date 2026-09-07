package com.jookmax.v7.domain.usecase


import com.jookmax.v7.core.model.MarketHistory
import com.jookmax.v7.domain.repository.MarketRepository

import javax.inject.Inject



class GetMarketHistoryUseCase @Inject constructor(

    private val repository: MarketRepository

) {


    suspend operator fun invoke(): MarketHistory? {


        return repository.getMarketHistory()


    }


}