package com.jookmax.v7.domain.usecase


import com.jookmax.v7.domain.repository.MarketRepository


class ClearMarketCacheUseCase(

    private val repository: MarketRepository

) {


    operator fun invoke() {

        repository.clearCache()

    }


}