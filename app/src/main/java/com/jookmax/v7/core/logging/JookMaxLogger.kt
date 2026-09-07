package com.jookmax.v7.core.logging


import javax.inject.Inject
import javax.inject.Singleton



/**
 * Default logger implementation for JookMax.
 *
 * Current:
 * - Keeps logs in memory
 * - Provides central logging point
 *
 * Future:
 * - Room persistence
 * - Log viewer UI
 * - Remote monitoring
 */
@Singleton
class JookMaxLogger @Inject constructor(

) : Logger {



    private val logs =
        mutableListOf<LogEntry>()





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


        logs.add(entry)



        // Temporary output
        // Future replacement:
        // Database / Monitoring pipeline

        println(
            "[${level.name}] $tag : $message"
        )


    }







    fun getLogs(): List<LogEntry> {


        return logs.toList()


    }





    fun clear() {


        logs.clear()


    }


}