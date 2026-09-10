package com.jookmax.v7.data.backup


import java.io.File
import java.security.MessageDigest

import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class BackupIntegrityValidator @Inject constructor() {



    fun calculateChecksum(
        file: File
    ): String {


        val digest =

            MessageDigest.getInstance(
                "SHA-256"
            )



        file.inputStream().use { input ->


            val buffer =
                ByteArray(8192)


            var bytesRead: Int



            while (
                input.read(buffer)
                    .also { bytesRead = it }
                != -1
            ) {


                digest.update(
                    buffer,
                    0,
                    bytesRead
                )

            }

        }



        return digest.digest()
            .joinToString("") {

                "%02x".format(it)

            }

    }





    fun validate(

        file: File,

        expectedChecksum: String

    ): BackupIntegrityResult {


        if (!file.exists()) {


            return BackupIntegrityResult(

                valid = false,

                checksum = "",

                message = "Backup file does not exist"

            )

        }



        val checksum =

            calculateChecksum(
                file
            )



        return BackupIntegrityResult(

            valid = checksum == expectedChecksum,

            checksum = checksum,

            message =
                if (checksum == expectedChecksum)

                    "Backup integrity verified"

                else

                    "Backup integrity failed"

        )

    }

}