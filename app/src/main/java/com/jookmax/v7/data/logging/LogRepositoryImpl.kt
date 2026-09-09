package com.jookmax.v7.data.logging


import com.jookmax.v7.core.logging.LogEntry
import com.jookmax.v7.core.logging.LogStorage
import com.jookmax.v7.domain.logging.LogRepository

import javax.inject.Inject
import javax.inject.Singleton



/**
 * Data implementation of LogRepository.
 *
 * Connects domain logging contract
 * with concrete storage implementation.
 */
@Singleton
class LogRepositoryImpl @Inject constructor(

    private val storage: LogStorage

) : LogRepository {



    override fun save(
        entry: LogEntry
    ) {

        storage.save(entry)

    }



    override fun getLogs(): List<LogEntry> {

        return storage.getLogs()

    }



    override fun clear() {

        storage.clear()

    }


}
