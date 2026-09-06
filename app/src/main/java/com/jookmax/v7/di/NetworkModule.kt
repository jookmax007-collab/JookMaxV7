package com.jookmax.v7.di


import com.jookmax.v7.data.remote.MarketApiService

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {



    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {


        return HttpLoggingInterceptor().apply {

            level = HttpLoggingInterceptor.Level.BODY

        }

    }



    @Provides
    @Singleton
    fun provideOkHttpClient(

        loggingInterceptor: HttpLoggingInterceptor

    ): OkHttpClient {


        return OkHttpClient.Builder()

            .addInterceptor(loggingInterceptor)

            .build()

    }



    @Provides
    @Singleton
    fun provideRetrofit(

        okHttpClient: OkHttpClient

    ): Retrofit {


        return Retrofit.Builder()

            .baseUrl(
                "https://example.com/"
            )

            .client(okHttpClient)

            .addConverterFactory(
                GsonConverterFactory.create()
            )

            .build()

    }



    @Provides
    @Singleton
    fun provideMarketApiService(

        retrofit: Retrofit

    ): MarketApiService {


        return retrofit.create(
            MarketApiService::class.java
        )

    }


}