package com.jookmax.v7.data.backup


data class BackupIntegrityResult(

    val valid: Boolean,

    val checksum: String,

    val message: String

)