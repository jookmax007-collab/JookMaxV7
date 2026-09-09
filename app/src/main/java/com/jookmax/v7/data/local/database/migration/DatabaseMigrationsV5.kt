package com.jookmax.v7.data.local.database.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


val MIGRATION_4_5 = object : Migration(4, 5) {


    override fun migrate(
        database: SupportSQLiteDatabase
    ) {


        database.execSQL(
            """
            CREATE TABLE IF NOT EXISTS decision_memory (

                id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,

                symbol TEXT NOT NULL,

                trend TEXT NOT NULL,

                rsi REAL NOT NULL,

                volatility REAL NOT NULL,

                action TEXT NOT NULL,

                confidence REAL NOT NULL,

                approved INTEGER NOT NULL,

                reward REAL NOT NULL,

                timestamp INTEGER NOT NULL

            )
            """.trimIndent()
        )



        database.execSQL(
            """
            CREATE INDEX IF NOT EXISTS index_decision_memory_timestamp

            ON decision_memory(timestamp)

            """.trimIndent()
        )



        database.execSQL(
            """
            CREATE INDEX IF NOT EXISTS index_decision_memory_symbol

            ON decision_memory(symbol)

            """.trimIndent()
        )


    }

}
