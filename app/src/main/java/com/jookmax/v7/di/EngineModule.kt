package com.jookmax.v7.di


import com.jookmax.v7.brain.BrainManager

import com.jookmax.v7.core.event.EventBus
import com.jookmax.v7.core.event.EventDispatcher

import com.jookmax.v7.engine.events.subscriber.DecisionEventSubscriber
import com.jookmax.v7.engine.events.subscriber.EngineEventSubscriber
import com.jookmax.v7.engine.events.subscriber.MarketEventSubscriber

import com.jookmax.v7.monitoring.EngineMonitor
import com.jookmax.v7.monitoring.MetricsCollector
import com.jookmax.v7.monitoring.RuntimeObserver

import com.jookmax.v7.engine.JookMaxEngine
import com.jookmax.v7.engine.market.MarketFeedManager
import com.jookmax.v7.engine.lifecycle.EngineLifecycleManager
import com.jookmax.v7.engine.manager.EngineManager
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


        engineEventSubscriber: EngineEventSubscriber,


        decisionEventSubscriber: DecisionEventSubscriber,


        engineMonitor: EngineMonitor,


        runtimeObserver: RuntimeObserver,


        metricsCollector: MetricsCollector,

        marketFeedManager: MarketFeedManager



    ): JookMaxEngine {




        return JookMaxEngine(



            lifecycleManager = lifecycleManager,


            runtimeTracker = runtimeTracker,


            coroutineScope = coroutineScope,


            brainManager = brainManager,


            eventBus = eventBus,


            eventDispatcher = eventDispatcher,


            marketEventSubscriber = marketEventSubscriber,


            engineEventSubscriber = engineEventSubscriber,


            decisionEventSubscriber = decisionEventSubscriber,


            engineMonitor = engineMonitor,


            runtimeObserver = runtimeObserver,


            metricsCollector = metricsCollector,

            marketFeedManager = marketFeedManager



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


