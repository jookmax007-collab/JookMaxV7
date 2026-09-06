package com.jookmax.v7.engine.runtime


class EngineRuntimeTracker {


    private var startTime: Long = 0L

    private var stopTime: Long = 0L



    fun start() {

        startTime = System.currentTimeMillis()
        stopTime = 0L

    }



    fun stop() {

        stopTime = System.currentTimeMillis()

    }



    fun getRuntimeMillis(): Long {

        return if (stopTime == 0L) {

            System.currentTimeMillis() - startTime

        } else {

            stopTime - startTime

        }

    }



    fun isRunning(): Boolean {

        return startTime != 0L && stopTime == 0L

    }



    fun reset(){

        startTime = 0L
        stopTime = 0L

    }
}