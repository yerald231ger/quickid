package org.qid.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.qid.core.infrastructure.FileRepository
import org.qid.viewModels.DescriptionValidator
import org.qid.viewModels.EditIdentityFileViewModel
import org.qid.viewModels.NameValidator

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class KoinInitializer(
    private val application: Application
) {
    actual fun init() {
        val container = AppContainer(QuickIdDatabaseFactory(application))
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
            viewModelOf(::EditIdentityFileViewModel)
        }

        startKoin {
            androidContext(application)
            androidLogger()
            modules(
                moduleRepository,
                viewModelModule,
                validatorsModule,
                editIdentityFileViewModel
            )
        }
    }
}