package org.qid.di

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.qid.core.infrastructure.FileRepository
import org.qid.infrastructure.repository.QuickIdFileRepository

class AppContainer(
    private val factory: QuickIdDatabaseFactory,
) {
    val fileRepository: FileRepository by lazy {
        QuickIdFileRepository (
            database = factory.createRoomDatabase(),
            scope = CoroutineScope(Dispatchers.Default + SupervisorJob()),
        )
    }
}
