package com.jookmax.v7.core.model


/**
 * Supported market analysis time frames.
 */
enum class TimeFrame(

    val minutes: Int

) {


    M1(1),

    M5(5),

    M15(15),

    H1(60),

    H4(240),

    D1(1440),

    W1(10080);



    companion object {


        fun fromMinutes(
            value: Int
        ): TimeFrame {


            return entries.firstOrNull {

                it.minutes == value

            } ?: M1


        }

    }


}