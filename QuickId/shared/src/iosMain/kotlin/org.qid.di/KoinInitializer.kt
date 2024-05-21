package org.qid.di

import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.qid.core.infrastructure.FileRepository

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class KoinInitializer {

    actual fun init() {
        val container = AppContainer(QuickIdDatabaseFactory())
        val repository = container.fileRepository

        val moduleRepository = module {
            single<FileRepository> { repository }
        }

        startKoin {
            modules(moduleRepository, viewModelModule)
        }
    }
}