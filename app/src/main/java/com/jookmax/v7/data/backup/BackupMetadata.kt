package com.jookmax.v7.data.backup


data class BackupMetadata(

    val backupId: String,

    val createdAt: Long,

    val appVersion: String,

    val databaseVersion: Int,

    val brainVersion: String,

    val backupType: String = "LOCAL"

)
