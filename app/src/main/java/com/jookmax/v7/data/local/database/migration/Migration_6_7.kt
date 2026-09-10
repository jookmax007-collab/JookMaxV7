package com.jookmax.v7.data.local.database.migration


import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase



val MIGRATION_6_7 = object : Migration(6, 7) {


    override fun migrate(
        db: SupportSQLiteDatabase
    ) {


        db.execSQL(
            """
            CREATE TABLE IF NOT EXISTS backup_records (

                id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,

                backupId TEXT NOT NULL,

                fileName TEXT NOT NULL,

                filePath TEXT NOT NULL,

                fileSize INTEGER NOT NULL,

                createdAt INTEGER NOT NULL,

                appVersion TEXT NOT NULL,

                databaseVersion INTEGER NOT NULL,

                brainVersion TEXT NOT NULL,

                backupType TEXT NOT NULL,

                encrypted INTEGER NOT NULL

            )
            """.trimIndent()
        )



        db.execSQL(
            """
            CREATE UNIQUE INDEX IF NOT EXISTS
            index_backup_records_backupId
            ON backup_records(backupId)
            """.trimIndent()
        )



        db.execSQL(
            """
            CREATE INDEX IF NOT EXISTS
            index_backup_records_createdAt
            ON backup_records(createdAt)
            """.trimIndent()
        )

    }

}
