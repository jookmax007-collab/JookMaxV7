package com.jookmax.v7.di


import com.jookmax.v7.analysis.indicator.ATR
import com.jookmax.v7.analysis.indicator.MACD
import com.jookmax.v7.analysis.indicator.MovingAverage
import com.jookmax.v7.analysis.indicator.RSI

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object IndicatorModule {


    @Provides
    @Singleton
    fun provideRSI(): RSI {

        return RSI()

    }


    @Provides
    @Singleton
    fun provideMovingAverage(): MovingAverage {

        return MovingAverage()

    }


    @Provides
    @Singleton
    fun provideMACD(): MACD {

        return MACD()

    }


    @Provides
    @Singleton
    fun provideATR(): ATR {

        return ATR()

    }

}