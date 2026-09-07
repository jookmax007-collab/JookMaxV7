package com.jookmax.v7.core.logging


import javax.inject.Inject
import javax.inject.Singleton



/**
 * Default logger implementation for JookMax.
 *
 * Responsibilities:
 * - Create log entries
 * - Forward logs to storage layer
 *
 * Storage responsibility belongs to LogStorage.
 */
@Singleton
class JookMaxLogger @Inject constructor(

    private val storage: LogStorage

) : Logger {



    override fun debug(
        tag: String,
        message: String
    ) {

        addLog(

            level = LogLevel.DEBUG,

            tag = tag,

            message = message

        )

    }





    override fun info(
        tag: String,
        message: String
    ) {

        addLog(

            level = LogLevel.INFO,

            tag = tag,

            message = message

        )

    }





    override fun warning(
        tag: String,
        message: String
    ) {

        addLog(

            level = LogLevel.WARNING,

            tag = tag,

            message = message

        )

    }





    override fun error(
        tag: String,
        message: String,
        throwable: Throwable?
    ) {

        addLog(

            level = LogLevel.ERROR,

            tag = tag,

            message = message,

            throwable = throwable

        )

    }





    private fun addLog(

        level: LogLevel,

        tag: String,

        message: String,

        throwable: Throwable? = null

    ) {


        val entry = LogEntry(

            level = level,

            tag = tag,

            message = message,

            timestamp = System.currentTimeMillis(),

            throwable = throwable

        )


        storage.save(entry)



        println(
            "[${level.name}] $tag : $message"
        )


    }


}