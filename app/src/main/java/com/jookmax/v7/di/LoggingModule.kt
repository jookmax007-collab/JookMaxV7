package com.jookmax.v7.di


import com.jookmax.v7.core.logging.JookMaxLogger
import com.jookmax.v7.core.logging.LogStorage
import com.jookmax.v7.core.logging.Logger
import com.jookmax.v7.data.logging.InMemoryLogStorage

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
abstract class LoggingModule {



    @Binds
    @Singleton
    abstract fun bindLogger(
        logger: JookMaxLogger
    ): Logger



    @Binds
    @Singleton
    abstract fun bindLogStorage(
        storage: InMemoryLogStorage
    ): LogStorage


}