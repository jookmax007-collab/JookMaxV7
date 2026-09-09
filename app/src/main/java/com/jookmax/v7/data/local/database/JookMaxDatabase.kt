package com.jookmax.v7.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase

import com.jookmax.v7.data.local.dao.DecisionDao
import com.jookmax.v7.data.local.dao.DecisionPatternDao
import com.jookmax.v7.data.local.dao.LearningExperienceDao
import com.jookmax.v7.data.local.dao.MarketDao
import com.jookmax.v7.data.local.dao.DecisionMemoryDao

import com.jookmax.v7.data.local.entity.DecisionEntity
import com.jookmax.v7.data.local.entity.DecisionPatternEntity
import com.jookmax.v7.data.local.entity.LearningExperienceEntity
import com.jookmax.v7.data.local.entity.MarketCandleEntity
import com.jookmax.v7.data.local.entity.MarketPriceEntity
import com.jookmax.v7.data.local.entity.DecisionMemoryEntity


@Database(
    entities = [
        MarketPriceEntity::class,
        MarketCandleEntity::class,
        DecisionEntity::class,
        LearningExperienceEntity::class,
        DecisionPatternEntity::class,
        DecisionMemoryEntity::class
    ],
    version = 5,
    exportSchema = true
)
abstract class JookMaxDatabase : RoomDatabase() {


    abstract fun marketDao(): MarketDao


    abstract fun decisionDao(): DecisionDao


    abstract fun learningExperienceDao(): LearningExperienceDao


    abstract fun decisionPatternDao(): DecisionPatternDao


    abstract fun decisionMemoryDao(): DecisionMemoryDao

}
