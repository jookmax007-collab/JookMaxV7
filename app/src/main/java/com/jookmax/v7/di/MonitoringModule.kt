package com.jookmax.v7.di


import com.jookmax.v7.core.monitoring.MetricsCollector
import com.jookmax.v7.core.monitoring.MetricsHistory

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
object MonitoringModule {



    @Provides
    @Singleton
    fun provideMetricsCollector():

            MetricsCollector {


        return MetricsCollector()


    }







    @Provides
    @Singleton
    fun provideMetricsHistory():

            MetricsHistory {


        return MetricsHistory()


    }



}