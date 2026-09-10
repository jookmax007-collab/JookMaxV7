package com.jookmax.v7.data.backup


data class BackupUploadResult(

    val success: Boolean,

    val backupId: String,

    val remotePath: String? = null,

    val message: String

)
