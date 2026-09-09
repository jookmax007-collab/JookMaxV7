package com.jookmax.v7.data.local.database.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_3_4 = object : Migration(3, 4) {

    override fun migrate(database: SupportSQLiteDatabase) {

        database.execSQL(
            """
            CREATE INDEX IF NOT EXISTS index_learning_experiences_timestamp
            ON learning_experiences(timestamp)
            """.trimIndent()
        )

        database.execSQL(
            """
            CREATE INDEX IF NOT EXISTS index_learning_experiences_symbol
            ON learning_experiences(symbol)
            """.trimIndent()
        )

        database.execSQL(
            """
            CREATE INDEX IF NOT EXISTS index_learning_experiences_marketRegime
            ON learning_experiences(marketRegime)
            """.trimIndent()
        )

        database.execSQL(
            """
            CREATE INDEX IF NOT EXISTS index_learning_experiences_brainVersion
            ON learning_experiences(brainVersion)
            """.trimIndent()
        )

        database.execSQL(
            """
            CREATE INDEX IF NOT EXISTS index_decision_patterns_patternName
            ON decision_patterns(patternName)
            """.trimIndent()
        )

        database.execSQL(
            """
            CREATE INDEX IF NOT EXISTS index_decision_patterns_marketRegime
            ON decision_patterns(marketRegime)
            """.trimIndent()
        )

        database.execSQL(
            """
            CREATE INDEX IF NOT EXISTS index_decision_patterns_timestamp
            ON decision_patterns(timestamp)
            """.trimIndent()
        )
    }
}
