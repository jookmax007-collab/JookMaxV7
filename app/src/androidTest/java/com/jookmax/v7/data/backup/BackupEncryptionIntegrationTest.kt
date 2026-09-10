package com.jookmax.v7.data.backup


import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

import org.junit.runner.RunWith



@RunWith(AndroidJUnit4::class)
class BackupEncryptionIntegrationTest {



    @Test
    fun encryptAndDecrypt_shouldReturnOriginalData() {



        val encryption =

            BackupEncryption()



        val originalJson =

            """
            {
                "backupId":"test_backup",
                "brainVersion":"intelligence-core",
                "databaseVersion":6,
                "memory":"decision-patterns"
            }
            """.trimIndent()





        val encrypted =

            encryption.encrypt(
                originalJson
            )



        assertNotEquals(

            originalJson,

            encrypted

        )





        val decrypted =

            encryption.decrypt(
                encrypted
            )



        assertEquals(

            originalJson,

            decrypted

        )

    }


}
