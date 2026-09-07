package com.jookmax.v7.engine.lifecycle


import com.jookmax.v7.core.logging.Logger

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class EngineLifecycleManager @Inject constructor(

    private val logger: Logger

) {



    private val _state =
        MutableStateFlow<EngineState>(EngineState.Idle)



    val state: StateFlow<EngineState>
        get() = _state.asStateFlow()





    fun start() {


        if (_state.value == EngineState.Running) {


            logger.warning(

                "EngineLifecycle",

                "Start ignored. Engine already running"

            )


            return

        }



        _state.value = EngineState.Starting



        logger.info(

            "EngineLifecycle",

            "Engine starting"

        )



        try {


            _state.value = EngineState.Running



            logger.info(

                "EngineLifecycle",

                "Engine started successfully"

            )


        } catch (e: Exception) {



            _state.value =
                EngineState.Error(

                    message = e.message ?: "Unknown error",

                    throwable = e

                )



            logger.error(

                "EngineLifecycle",

                "Engine start failed",

                e

            )

        }


    }







    fun pause() {


        if (_state.value == EngineState.Running) {


            _state.value = EngineState.Paused



            logger.info(

                "EngineLifecycle",

                "Engine paused"

            )


        }

    }







    fun resume() {


        if (_state.value == EngineState.Paused) {


            _state.value = EngineState.Running



            logger.info(

                "EngineLifecycle",

                "Engine resumed"

            )


        }

    }







    fun stop() {



        _state.value = EngineState.Stopping



        logger.info(

            "EngineLifecycle",

            "Engine stopping"

        )



        _state.value = EngineState.Stopped



        logger.info(

            "EngineLifecycle",

            "Engine stopped"

        )


    }







    fun reset() {


        _state.value = EngineState.Idle



        logger.info(

            "EngineLifecycle",

            "Engine reset"

        )


    }


}