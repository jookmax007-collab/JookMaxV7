package com.jookmax.v7.data.local.entity


import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey



@Entity(
    tableName = "backup_records",
    indices = [
        Index(
            value = ["backupId"],
            unique = true
        ),
        Index(
            value = ["createdAt"]
        )
    ]
)
data class BackupRecordEntity(


    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,


    val backupId: String,


    val fileName: String,


    val filePath: String,


    val fileSize: Long,


    val createdAt: Long,


    val appVersion: String,


    val databaseVersion: Int,


    val brainVersion: String,


    val backupType: String,


    val encrypted: Boolean

)
