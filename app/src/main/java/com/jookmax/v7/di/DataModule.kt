package com.jookmax.v7.di

import com.jookmax.v7.data.local.MarketLocalDataSource

import com.jookmax.v7.data.mapper.CandleMapper
import com.jookmax.v7.data.mapper.MarketEntityMapper
import com.jookmax.v7.data.mapper.MarketQuoteMapper
import com.jookmax.v7.data.mapper.MarketRemoteMapper
import com.jookmax.v7.data.mapper.TickMapper

import com.jookmax.v7.data.remote.MarketApiService
import com.jookmax.v7.data.remote.MarketRemoteDataSource
import com.jookmax.v7.data.remote.socket.MarketSocketClient

import com.jookmax.v7.data.repository.CandleRepositoryImpl
import com.jookmax.v7.data.repository.DecisionAnalyticsRepositoryImpl
import com.jookmax.v7.data.repository.LearningRepositoryImpl
import com.jookmax.v7.data.repository.MarketRepositoryImpl
import com.jookmax.v7.data.repository.RewardExperienceRepositoryImpl

import com.jookmax.v7.domain.analytics.DecisionAnalyticsRepository
import com.jookmax.v7.domain.repository.CandleRepository
import com.jookmax.v7.domain.repository.LearningRepository
import com.jookmax.v7.domain.repository.MarketDataSource
import com.jookmax.v7.domain.repository.MarketRepository
import com.jookmax.v7.domain.repository.RewardExperienceRepository

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
    fun provideMarketRemoteMapper(): MarketRemoteMapper {

        return MarketRemoteMapper()

    }



    @Provides
    @Singleton
    fun provideMarketQuoteMapper(): MarketQuoteMapper {

        return MarketQuoteMapper()

    }



    @Provides
    @Singleton
    fun provideTickMapper(): TickMapper {

        return TickMapper()

    }



    @Provides
    @Singleton
    fun provideCandleMapper(): CandleMapper {

        return CandleMapper()

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
        apiService: MarketApiService,
        marketRemoteMapper: MarketRemoteMapper,
        marketQuoteMapper: MarketQuoteMapper,
        tickMapper: TickMapper,
        candleMapper: CandleMapper,
        marketSocketClient: MarketSocketClient
    ): MarketRemoteDataSource {

        return MarketRemoteDataSource(
            apiService = apiService,
            marketRemoteMapper = marketRemoteMapper,
            marketQuoteMapper = marketQuoteMapper,
            tickMapper = tickMapper,
            candleMapper = candleMapper,
            marketSocketClient = marketSocketClient
        )

    }



    @Provides
    @Singleton
    fun provideMarketDataSource(
        remoteDataSource: MarketRemoteDataSource
    ): MarketDataSource {

        return remoteDataSource

    }



    @Provides
    @Singleton
    fun provideCandleRepository(
        implementation: CandleRepositoryImpl
    ): CandleRepository {

        return implementation

    }



    @Provides
    @Singleton
    fun provideMarketRepository(
        marketDataSource: MarketDataSource,
        localDataSource: MarketLocalDataSource
    ): MarketRepository {

        return MarketRepositoryImpl(
            remoteDataSource = marketDataSource,
            localDataSource = localDataSource
        )

    }



    @Provides
    @Singleton
    fun provideMarketSocketClient(
        okHttpClient: okhttp3.OkHttpClient
    ): MarketSocketClient {

        return MarketSocketClient(
            apiKey = "",
            okHttpClient = okHttpClient
        )

    }



    @Provides
    @Singleton
    fun provideDecisionAnalyticsRepository(
        implementation: DecisionAnalyticsRepositoryImpl
    ): DecisionAnalyticsRepository {

        return implementation

    }



    @Provides
    @Singleton
    fun provideLearningRepository(
        implementation: LearningRepositoryImpl
    ): LearningRepository {

        return implementation

    }



    @Provides
    @Singleton
    fun provideRewardExperienceRepository(
        implementation: RewardExperienceRepositoryImpl
    ): RewardExperienceRepository {

        return implementation

    }

}