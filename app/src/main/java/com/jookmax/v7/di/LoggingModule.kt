package com.jookmax.v7.di


import com.jookmax.v7.core.logging.JookMaxLogger
import com.jookmax.v7.core.logging.Logger

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import javax.inject.Singleton



/**
 * Provides logging dependencies.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class LoggingModule {



    @Binds
    @Singleton
    abstract fun bindLogger(

        logger: JookMaxLogger

    ): Logger


}