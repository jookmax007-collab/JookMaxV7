package com.jookmax.v7.data.local.database.migration


import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase



val MIGRATION_7_8 = object : Migration(7,8) {


    override fun migrate(
        db: SupportSQLiteDatabase
    ) {


        db.execSQL(
            """
            CREATE TABLE IF NOT EXISTS reward_experiences (

                id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,

                action TEXT NOT NULL,

                entryPrice REAL NOT NULL,

                exitPrice REAL NOT NULL,

                reward REAL NOT NULL,

                success INTEGER NOT NULL,

                reason TEXT NOT NULL,

                timestamp INTEGER NOT NULL

            )
            """.trimIndent()
        )


        db.execSQL(
            """
            CREATE INDEX IF NOT EXISTS
            index_reward_experiences_timestamp
            ON reward_experiences(timestamp)
            """.trimIndent()
        )


    }

}