package com.jookmax.v7.di


import android.content.Context
import androidx.room.Room


import com.jookmax.v7.data.local.dao.BackupRecordDao
import com.jookmax.v7.data.local.dao.DecisionDao
import com.jookmax.v7.data.local.dao.DecisionMemoryDao
import com.jookmax.v7.data.local.dao.DecisionPatternDao
import com.jookmax.v7.data.local.dao.LearningExperienceDao
import com.jookmax.v7.data.local.dao.MarketDao
import com.jookmax.v7.data.local.dao.RewardExperienceDao


import com.jookmax.v7.data.local.database.JookMaxDatabase


import com.jookmax.v7.data.local.database.migration.MIGRATION_2_3
import com.jookmax.v7.data.local.database.migration.MIGRATION_3_4
import com.jookmax.v7.data.local.database.migration.MIGRATION_4_5
import com.jookmax.v7.data.local.database.migration.MIGRATION_5_6
import com.jookmax.v7.data.local.database.migration.MIGRATION_6_7
import com.jookmax.v7.data.local.database.migration.MIGRATION_7_8


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

        )

            .addMigrations(

                MIGRATION_2_3,

                MIGRATION_3_4,

                MIGRATION_4_5,

                MIGRATION_5_6,

                MIGRATION_6_7,

                MIGRATION_7_8

            )

            .build()

    }





    @Provides
    @Singleton
    fun provideMarketDao(

        database: JookMaxDatabase

    ): MarketDao {

        return database.marketDao()

    }





    @Provides
    @Singleton
    fun provideDecisionDao(

        database: JookMaxDatabase

    ): DecisionDao {

        return database.decisionDao()

    }





    @Provides
    @Singleton
    fun provideLearningExperienceDao(

        database: JookMaxDatabase

    ): LearningExperienceDao {

        return database.learningExperienceDao()

    }





    @Provides
    @Singleton
    fun provideDecisionPatternDao(

        database: JookMaxDatabase

    ): DecisionPatternDao {

        return database.decisionPatternDao()

    }





    @Provides
    @Singleton
    fun provideDecisionMemoryDao(

        database: JookMaxDatabase

    ): DecisionMemoryDao {

        return database.decisionMemoryDao()

    }





    @Provides
    @Singleton
    fun provideBackupRecordDao(

        database: JookMaxDatabase

    ): BackupRecordDao {

        return database.backupRecordDao()

    }





    @Provides
    @Singleton
    fun provideRewardExperienceDao(

        database: JookMaxDatabase

    ): RewardExperienceDao {

        return database.rewardExperienceDao()

    }


}