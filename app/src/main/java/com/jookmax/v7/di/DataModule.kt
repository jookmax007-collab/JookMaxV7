package com.jookmax.v7.di


import com.jookmax.v7.data.local.MarketLocalDataSource
import com.jookmax.v7.data.mapper.MarketMapper
import com.jookmax.v7.data.remote.MarketRemoteDataSource
import com.jookmax.v7.data.repository.MarketRepositoryImpl
import com.jookmax.v7.domain.repository.MarketRepository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
object DataModule {



    @Provides
    @Singleton
    fun provideMarketLocalDataSource()
            : MarketLocalDataSource {

        return MarketLocalDataSource()

    }




    @Provides
    @Singleton
    fun provideMarketRemoteDataSource()
            : MarketRemoteDataSource {

        return MarketRemoteDataSource()

    }




    @Provides
    @Singleton
    fun provideMarketMapper()
            : MarketMapper {

        return MarketMapper()

    }




    @Provides
    @Singleton
    fun provideMarketRepository(

        remoteDataSource: MarketRemoteDataSource,

        localDataSource: MarketLocalDataSource,

        mapper: MarketMapper

    ): MarketRepository {


        return MarketRepositoryImpl(

            remoteDataSource,

            localDataSource,

            mapper

        )

    }


}