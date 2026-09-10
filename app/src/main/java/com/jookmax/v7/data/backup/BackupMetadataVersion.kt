package com.jookmax.v7.data.backup


data class BackupMetadataVersion(

    val backupSchemaVersion: Int,

    val appVersion: String,

    val databaseVersion: Int,

    val brainVersion: String,

    val createdAt: Long

)
