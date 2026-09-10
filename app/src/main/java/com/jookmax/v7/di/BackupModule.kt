package com.jookmax.v7.di


import com.jookmax.v7.data.backup.BackupRecordRepository
import com.jookmax.v7.data.backup.BackupRecordRepositoryImpl

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
abstract class BackupModule {


    @Binds
    @Singleton
    abstract fun bindBackupRecordRepository(
        implementation: BackupRecordRepositoryImpl
    ): BackupRecordRepository


}
