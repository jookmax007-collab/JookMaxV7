package com.jookmax.v7.data.backup


import android.util.Base64

import java.nio.charset.StandardCharsets

import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class BackupEncryption @Inject constructor() {



    private val algorithm = "AES"



    private val secretKey =

        "JookMaxV7BackupKey"

            .padEnd(
                16,
                '0'
            )
            .substring(
                0,
                16
            )





    fun encrypt(

        plainText: String

    ): String {



        val cipher =

            Cipher.getInstance(
                algorithm
            )



        val key =

            SecretKeySpec(

                secretKey.toByteArray(
                    StandardCharsets.UTF_8
                ),

                algorithm

            )



        cipher.init(

            Cipher.ENCRYPT_MODE,

            key

        )



        val encrypted =

            cipher.doFinal(

                plainText.toByteArray(
                    StandardCharsets.UTF_8
                )

            )



        return Base64.encodeToString(

            encrypted,

            Base64.DEFAULT

        )

    }






    fun decrypt(

        encryptedText: String

    ): String {



        val cipher =

            Cipher.getInstance(
                algorithm
            )



        val key =

            SecretKeySpec(

                secretKey.toByteArray(
                    StandardCharsets.UTF_8
                ),

                algorithm

            )



        cipher.init(

            Cipher.DECRYPT_MODE,

            key

        )



        val decoded =

            Base64.decode(

                encryptedText,

                Base64.DEFAULT

            )



        return String(

            cipher.doFinal(
                decoded
            ),

            StandardCharsets.UTF_8

        )

    }


}
