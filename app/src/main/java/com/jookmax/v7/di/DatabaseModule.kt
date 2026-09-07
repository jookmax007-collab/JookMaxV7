package com.jookmax.v7.di


import android.content.Context
import androidx.room.Room
import com.jookmax.v7.data.local.dao.MarketDao
import com.jookmax.v7.data.local.database.JookMaxDatabase

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {



    @Provides
    @Singleton
    fun provideJookMaxDatabase(
        @ApplicationContext context: Context
    ): JookMaxDatabase {


        return Room.databaseBuilder(

            context,

            JookMaxDatabase::class.java,

            "jookmax_database"

        ).build()


    }



    @Provides
    @Singleton
    fun provideMarketDao(
        database: JookMaxDatabase
    ): MarketDao {


        return database.marketDao()


    }


}