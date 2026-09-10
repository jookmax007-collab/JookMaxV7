package com.jookmax.v7.data.local.database.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


val MIGRATION_2_3 = object : Migration(2, 3) {

    override fun migrate(database: SupportSQLiteDatabase) {

        database.execSQL(
            """
            CREATE TABLE IF NOT EXISTS learning_experiences (
                id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                decision TEXT NOT NULL,
                confidence REAL NOT NULL,
                riskApproved INTEGER NOT NULL,
                positionSize REAL NOT NULL,
                riskScore REAL NOT NULL,
                symbol TEXT NOT NULL,
                price REAL NOT NULL,
                timeframe TEXT NOT NULL,
                marketRegime TEXT NOT NULL,
                trendState TEXT NOT NULL,
                volatilityState TEXT NOT NULL,
                rsi REAL NOT NULL,
                macd REAL NOT NULL,
                movingAverage REAL NOT NULL,
                atr REAL NOT NULL,
                supportLevel REAL NOT NULL,
                resistanceLevel REAL NOT NULL,
                reward REAL NOT NULL,
                profitLoss REAL NOT NULL,
                success INTEGER NOT NULL,
                holdingTime INTEGER NOT NULL,
                drawdown REAL NOT NULL,
                schemaVersion INTEGER NOT NULL,
                brainVersion TEXT NOT NULL,
                strategyVersion TEXT NOT NULL,
                featureVersion TEXT NOT NULL,
                timestamp INTEGER NOT NULL
            )
            """.trimIndent()
        )


        database.execSQL(
            """
            CREATE TABLE IF NOT EXISTS decision_patterns (
                id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                patternName TEXT NOT NULL,
                marketRegime TEXT NOT NULL,
                trendState TEXT NOT NULL,
                volatilityState TEXT NOT NULL,
                successfulCount INTEGER NOT NULL,
                failedCount INTEGER NOT NULL,
                averageReward REAL NOT NULL,
                confidenceScore REAL NOT NULL,
                usageCount INTEGER NOT NULL,
                brainVersion TEXT NOT NULL,
                timestamp INTEGER NOT NULL
            )
            """.trimIndent()
        )
    }
}



val MIGRATION_4_5 = object : Migration(4, 5) {

    override fun migrate(database: SupportSQLiteDatabase) {


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

    }
}