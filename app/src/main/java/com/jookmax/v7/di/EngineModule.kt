package com.jookmax.v7.di


import com.jookmax.v7.brain.BrainManager
import com.jookmax.v7.core.events.EventBus

import com.jookmax.v7.engine.JookMaxEngine
import com.jookmax.v7.engine.manager.EngineManager
import com.jookmax.v7.engine.lifecycle.EngineLifecycleManager
import com.jookmax.v7.engine.runtime.EngineCoroutineScope
import com.jookmax.v7.engine.runtime.EngineRuntimeTracker


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


import javax.inject.Singleton




@Module
@InstallIn(SingletonComponent::class)
object EngineModule {





    @Provides
    @Singleton
    fun provideEngineLifecycleManager()
            : EngineLifecycleManager {


        return EngineLifecycleManager()


    }







    @Provides
    @Singleton
    fun provideEngineRuntimeTracker()
            : EngineRuntimeTracker {


        return EngineRuntimeTracker()


    }







    @Provides
    @Singleton
    fun provideEngineCoroutineScope()
            : EngineCoroutineScope {


        return EngineCoroutineScope()


    }







    @Provides
    @Singleton
    fun provideJookMaxEngine(

        lifecycleManager: EngineLifecycleManager,

        runtimeTracker: EngineRuntimeTracker,

        coroutineScope: EngineCoroutineScope,

        brainManager: BrainManager,

        eventBus: EventBus

    ): JookMaxEngine {



        return JookMaxEngine(

            lifecycleManager = lifecycleManager,

            runtimeTracker = runtimeTracker,

            coroutineScope = coroutineScope,

            brainManager = brainManager,

            eventBus = eventBus

        )


    }







    @Provides
    @Singleton
    fun provideEngineManager(

        engine: JookMaxEngine

    ): EngineManager {



        return EngineManager(

            engine = engine

        )


    }



}