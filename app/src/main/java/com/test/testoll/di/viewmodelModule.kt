package com.test.testoll.di

import com.test.testoll.data.ProgramViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ProgramViewModel(get()) }
}
