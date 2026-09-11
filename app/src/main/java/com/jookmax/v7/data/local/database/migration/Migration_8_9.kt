package com.jookmax.v7.data.local.database.migration


import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


val MIGRATION_8_9 = object : Migration(8,9) {


    override fun migrate(
        db: SupportSQLiteDatabase
    ) {


        db.execSQL(
            """
            ALTER TABLE decision_memory
            ADD COLUMN marketRegime TEXT NOT NULL DEFAULT 'UNKNOWN'
            """.trimIndent()
        )


        db.execSQL(
            """
            ALTER TABLE decision_memory
            ADD COLUMN session TEXT NOT NULL DEFAULT 'UNKNOWN'
            """.trimIndent()
        )


        db.execSQL(
            """
            ALTER TABLE decision_memory
            ADD COLUMN dxy REAL NOT NULL DEFAULT 0.0
            """.trimIndent()
        )


        db.execSQL(
            """
            ALTER TABLE decision_memory
            ADD COLUMN yield REAL NOT NULL DEFAULT 0.0
            """.trimIndent()
        )


        db.execSQL(
            """
            ALTER TABLE decision_memory
            ADD COLUMN newsRisk REAL NOT NULL DEFAULT 0.0
            """.trimIndent()
        )

    }

}