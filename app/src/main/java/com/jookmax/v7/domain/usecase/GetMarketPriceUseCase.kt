package com.jookmax.v7.domain.usecase


import com.jookmax.v7.core.model.MarketPrice
import com.jookmax.v7.domain.repository.MarketRepository


class GetMarketPriceUseCase(

    private val repository: MarketRepository

) {


    suspend operator fun invoke(): MarketPrice? {


        return repository.getLatestMarketPrice()

    }


}