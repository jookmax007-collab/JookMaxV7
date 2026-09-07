package com.jookmax.v7.data.local.database


import androidx.room.Database
import androidx.room.RoomDatabase
import com.jookmax.v7.data.local.dao.MarketDao
import com.jookmax.v7.data.local.entity.MarketCandleEntity
import com.jookmax.v7.data.local.entity.MarketPriceEntity



@Database(

    entities = [
        MarketPriceEntity::class,
        MarketCandleEntity::class
    ],

    version = 1,

    exportSchema = false

)
abstract class JookMaxDatabase : RoomDatabase() {


    abstract fun marketDao(): MarketDao


}