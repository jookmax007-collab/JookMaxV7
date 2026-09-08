package com.jookmax.v7.data.local.database


import androidx.room.Database
import androidx.room.RoomDatabase

import com.jookmax.v7.data.local.dao.MarketDao
import com.jookmax.v7.data.local.dao.DecisionDao

import com.jookmax.v7.data.local.entity.MarketCandleEntity
import com.jookmax.v7.data.local.entity.MarketPriceEntity
import com.jookmax.v7.data.local.entity.DecisionEntity



@Database(

    entities = [

        MarketPriceEntity::class,

        MarketCandleEntity::class,

        DecisionEntity::class

    ],

    version = 2,

    exportSchema = false

)
abstract class JookMaxDatabase : RoomDatabase() {



    abstract fun marketDao(): MarketDao



    abstract fun decisionDao(): DecisionDao



}