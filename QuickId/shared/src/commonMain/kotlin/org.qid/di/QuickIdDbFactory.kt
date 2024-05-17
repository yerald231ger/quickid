package org.qid.di

import org.qid.infrastructure.database.QuickIdDatabase

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class QuickIdDatabaseFactory {
    fun createRoomDatabase(): QuickIdDatabase
}