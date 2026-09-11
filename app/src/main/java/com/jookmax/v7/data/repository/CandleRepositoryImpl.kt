package com.jookmax.v7.data.repository


import com.jookmax.v7.core.model.MarketCandle

import com.jookmax.v7.data.local.dao.MarketDao
import com.jookmax.v7.data.mapper.MarketEntityMapper

import com.jookmax.v7.domain.repository.CandleRepository

import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class CandleRepositoryImpl @Inject constructor(


    private val marketDao: MarketDao,


    private val mapper: MarketEntityMapper


) : CandleRepository {



    override suspend fun getCandles():

            List<MarketCandle> {


        return marketDao
            .getCandles()
            .map {


                mapper.mapCandleToDomain(it)


            }


    }





    override suspend fun saveCandles(

        candles: List<MarketCandle>

    ) {


        val entities =

            candles.map {


                mapper.mapCandleToEntity(it)


            }



        marketDao.insertCandles(

            entities

        )


    }







    override suspend fun clear() {


        marketDao.clearCandles()


    }


}
