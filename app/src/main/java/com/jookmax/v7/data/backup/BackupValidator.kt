package com.jookmax.v7.data.backup

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BackupValidator @Inject constructor() {

    fun isValid(
        backupJson: String
    ): Boolean {

        val content = backupJson.trim()

        if (content.isEmpty()) {
            return false
        }

        if (!content.startsWith("{") || !content.endsWith("}")) {
            return false
        }

        val requiredFields = listOf(
            "\"backupId\"",
            "\"createdAt\"",
            "\"appVersion\"",
            "\"databaseVersion\"",
            "\"brainVersion\"",
            "\"backupType\""
        )

        return requiredFields.all { field ->
            content.contains(field)
        }
    }
}
