package com.jookmax.v7.engine.manager

import com.jookmax.v7.engine.JookMaxEngine


class EngineManager(
    private val engine: JookMaxEngine
) {


    fun startEngine() {

        engine.start()

    }



    fun pauseEngine() {

        engine.pause()

    }



    fun resumeEngine() {

        engine.resume()

    }



    fun stopEngine() {

        engine.stop()

    }



    fun resetEngine() {

        engine.reset()

    }



    fun getEngine(): JookMaxEngine {

        return engine

    }
}