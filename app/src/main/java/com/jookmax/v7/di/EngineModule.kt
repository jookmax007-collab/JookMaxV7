package com.jookmax.v7.di


import com.jookmax.v7.brain.BrainManager

import com.jookmax.v7.core.events.EventBus
import com.jookmax.v7.core.events.EventDispatcher
import com.jookmax.v7.core.events.subscriber.EngineEventSubscriber
import com.jookmax.v7.core.events.subscriber.MarketEventSubscriber

import com.jookmax.v7.engine.JookMaxEngine
import com.jookmax.v7.engine.manager.EngineManager
import com.jookmax.v7.engine.runtime.EngineCoroutineScope
import com.jookmax.v7.engine.runtime.EngineRuntimeTracker
import com.jookmax.v7.engine.lifecycle.EngineLifecycleManager


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


        eventBus: EventBus,


        eventDispatcher: EventDispatcher,


        marketEventSubscriber: MarketEventSubscriber,


        engineEventSubscriber: EngineEventSubscriber



    ): JookMaxEngine {



        return JookMaxEngine(


            lifecycleManager = lifecycleManager,


            runtimeTracker = runtimeTracker,


            coroutineScope = coroutineScope,


            brainManager = brainManager,


            eventBus = eventBus,


            eventDispatcher = eventDispatcher,


            marketEventSubscriber = marketEventSubscriber,


            engineEventSubscriber = engineEventSubscriber


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