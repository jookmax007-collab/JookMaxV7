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

import java.util.concurrent.TimeUnit

import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {



    private const val BASE_URL =
        "https://api.example.com/"



    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor()
            : HttpLoggingInterceptor {


        return HttpLoggingInterceptor().apply {

            level =
                HttpLoggingInterceptor.Level.BODY

        }

    }




    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    )
            : OkHttpClient {


        return OkHttpClient.Builder()

            .addInterceptor(
                loggingInterceptor
            )

            .connectTimeout(
                30,
                TimeUnit.SECONDS
            )

            .readTimeout(
                30,
                TimeUnit.SECONDS
            )

            .writeTimeout(
                30,
                TimeUnit.SECONDS
            )

            .build()

    }




    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    )
            : Retrofit {


        return Retrofit.Builder()

            .baseUrl(
                BASE_URL
            )

            .client(
                okHttpClient
            )

            .addConverterFactory(
                GsonConverterFactory.create()
            )

            .build()

    }




    @Provides
    @Singleton
    fun provideMarketApiService(
        retrofit: Retrofit
    )
            : MarketApiService {


        return retrofit.create(
            MarketApiService::class.java
        )

    }


}
