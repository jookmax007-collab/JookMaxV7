package com.jookmax.v7.data.local.database.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


val MIGRATION_5_6 = object : Migration(5, 6) {

    override fun migrate(
        db: SupportSQLiteDatabase
    ) {

        db.execSQL(
            """
            CREATE INDEX IF NOT EXISTS index_decision_memory_symbol_trend
            ON decision_memory(symbol, trend)
            """.trimIndent()
        )


        db.execSQL(
            """
            CREATE INDEX IF NOT EXISTS index_decision_memory_timestamp
            ON decision_memory(timestamp)
            """.trimIndent()
        )

    }
}
