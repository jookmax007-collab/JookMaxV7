package com.jookmax.v7.di


import com.jookmax.v7.data.local.MarketLocalDataSource
import com.jookmax.v7.data.mapper.MarketEntityMapper
import com.jookmax.v7.data.remote.MarketRemoteDataSource
import com.jookmax.v7.data.repository.MarketRepositoryImpl

import com.jookmax.v7.domain.repository.MarketDataSource
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
    fun provideMarketEntityMapper(): MarketEntityMapper {


        return MarketEntityMapper()


    }





    @Provides
    @Singleton
    fun provideMarketLocalDataSource(

        marketDao: com.jookmax.v7.data.local.dao.MarketDao,

        entityMapper: MarketEntityMapper

    ): MarketLocalDataSource {


        return MarketLocalDataSource(

            marketDao = marketDao,

            mapper = entityMapper

        )


    }





    @Provides
    @Singleton
    fun provideMarketRemoteDataSource(

        apiService: com.jookmax.v7.data.remote.MarketApiService

    ): MarketRemoteDataSource {


        return MarketRemoteDataSource(

            apiService = apiService

        )


    }





    /**
     * Domain contract binding
     *
     * Domain layer depends on abstraction,
     * not concrete implementation.
     */
    @Provides
    @Singleton
    fun provideMarketDataSource(

        remoteDataSource: MarketRemoteDataSource

    ): MarketDataSource {


        return remoteDataSource


    }





    @Provides
    @Singleton
    fun provideMarketRepository(

        remoteDataSource: MarketDataSource,

        localDataSource: MarketLocalDataSource

    ): MarketRepository {


        return MarketRepositoryImpl(

            remoteDataSource = remoteDataSource,

            localDataSource = localDataSource

        )


    }



}