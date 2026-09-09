package com.jookmax.v7.di


import com.jookmax.v7.brain.pipeline.BrainExecutor
import com.jookmax.v7.brain.pipeline.DefaultBrainExecutor

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent



@Module
@InstallIn(SingletonComponent::class)
abstract class BrainBindingModule {


    @Binds
    abstract fun bindBrainExecutor(

        executor: DefaultBrainExecutor

    ): BrainExecutor


}
