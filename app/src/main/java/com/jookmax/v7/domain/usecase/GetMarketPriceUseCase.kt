package com.jookmax.v7.domain.usecase

import com.jookmax.v7.core.model.MarketPrice
import com.jookmax.v7.domain.repository.MarketRepository
import javax.inject.Inject


class GetCachedMarketPriceUseCase @Inject constructor(

    private val repository: MarketRepository

) {


    operator fun invoke(): MarketPrice? {

        return repository.getCachedMarketPrice()

    }

}