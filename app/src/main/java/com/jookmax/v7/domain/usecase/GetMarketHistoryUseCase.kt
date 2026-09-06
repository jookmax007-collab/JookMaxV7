package com.jookmax.v7.domain.usecase


import com.jookmax.v7.core.model.MarketHistory
import com.jookmax.v7.domain.repository.MarketRepository


class GetMarketHistoryUseCase(

    private val repository: MarketRepository

) {


    suspend operator fun invoke(): MarketHistory? {


        return repository.getMarketHistory()

    }

}