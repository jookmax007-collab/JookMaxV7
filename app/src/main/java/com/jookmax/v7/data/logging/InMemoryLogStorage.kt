package com.jookmax.v7.data.logging


import com.jookmax.v7.core.logging.LogEntry
import com.jookmax.v7.core.logging.LogStorage

import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class InMemoryLogStorage @Inject constructor(

) : LogStorage {



    private val logs =
        mutableListOf<LogEntry>()



    override fun save(
        entry: LogEntry
    ) {

        logs.add(entry)

    }



    override fun getLogs(): List<LogEntry> {

        return logs.toList()

    }



    override fun clear() {

        logs.clear()

    }


}