package org.qid.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import org.qid.viewModels.IndexViewModel

actual val viewModelModule = module {
    viewModelOf(::IndexViewModel)
}