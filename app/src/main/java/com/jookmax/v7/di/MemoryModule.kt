package com.jookmax.v7.di


import com.jookmax.v7.data.repository.PersistentDecisionMemoryRepositoryImpl
import com.jookmax.v7.domain.repository.PersistentDecisionMemoryRepository

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
    abstract fun bindPersistentDecisionMemoryRepository(
        impl: PersistentDecisionMemoryRepositoryImpl
    ):
            PersistentDecisionMemoryRepository


}
