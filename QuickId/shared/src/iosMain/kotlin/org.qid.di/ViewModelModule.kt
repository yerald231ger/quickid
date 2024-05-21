package org.qid.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.qid.viewModels.IndexViewModel

actual val viewModelModule = module {
    singleOf(::IndexViewModel)
}