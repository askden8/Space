package com.shevchenko.space_x.di

import com.shevchenko.space_x.data.DataRepository
import com.shevchenko.space_x.data.DataRepositoryImp
import com.shevchenko.space_x.ui.main.RocketsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val appModule: Module = module {

    single<DataRepository> { DataRepositoryImp(get()) }

    // MyViewModel ViewModel
    viewModel { RocketsViewModel(get(), androidContext().getSharedPreferences("test",0)) }
}
