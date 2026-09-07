package com.jookmax.v7.domain.usecase


import com.jookmax.v7.domain.repository.MarketRepository
import javax.inject.Inject



class ClearMarketCacheUseCase @Inject constructor(

    private val repository: MarketRepository

) {



    suspend operator fun invoke() {


        repository.clearCache()


    }


}