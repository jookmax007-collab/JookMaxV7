package com.jookmax.v7.di


import com.jookmax.v7.brain.intelligence.memory.DecisionMemory
import com.jookmax.v7.brain.intelligence.memory.DecisionMemoryImpl

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
abstract class MemoryModule {



    @Binds
    @Singleton
    abstract fun bindDecisionMemory(
        impl: DecisionMemoryImpl
    ):
            DecisionMemory

}