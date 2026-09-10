package com.jookmax.v7.data.local.database


import androidx.room.testing.MigrationTestHelper
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry

import com.jookmax.v7.data.local.database.migration.MIGRATION_2_3
import com.jookmax.v7.data.local.database.migration.MIGRATION_3_4
import com.jookmax.v7.data.local.database.migration.MIGRATION_4_5

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import java.io.IOException


@RunWith(AndroidJUnit4::class)
class DatabaseMigrationTest {


    private val TEST_DB = "migration-test"


    @get:Rule
    val helper = MigrationTestHelper(
        InstrumentationRegistry.getInstrumentation(),
        JookMaxDatabase::class.java,
        emptyList(),
        FrameworkSQLiteOpenHelperFactory()
    )


    @Test
    @Throws(IOException::class)
    fun migrate2To5() {


        helper.createDatabase(
            TEST_DB,
            2
        ).close()


        helper.runMigrationsAndValidate(
            TEST_DB,
            5,
            true,
            MIGRATION_2_3,
            MIGRATION_3_4,
            MIGRATION_4_5
        )

    }


}
