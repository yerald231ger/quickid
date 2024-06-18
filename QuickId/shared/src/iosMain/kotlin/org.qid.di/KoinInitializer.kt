package org.qid.di

import org.koin.core.context.startKoin
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.qid.core.infrastructure.FileRepository
import org.qid.viewModels.DescriptionValidator
import org.qid.viewModels.EditIdentityFileViewModel
import org.qid.viewModels.NameValidator

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class KoinInitializer {

    actual fun init() {
        val container = AppContainer(QuickIdDatabaseFactory())
        val repository = container.fileRepository

        val moduleRepository = module {
            single<FileRepository> { repository }
        }

        val validatorsModule = module {
            single {
                NameValidator()
            }
            single {
                DescriptionValidator()
            }
        }

        val editIdentityFileViewModel = module {
            singleOf(::EditIdentityFileViewModel)
        }

        startKoin {
            modules(
                moduleRepository,
                viewModelModule,
                validatorsModule,
                editIdentityFileViewModel
            )
        }
    }
}