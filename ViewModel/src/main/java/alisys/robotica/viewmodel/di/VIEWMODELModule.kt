package alisys.robotica.viewmodel.di

import alisys.robotica.viewmodel.LanguageViewModel
import alisys.robotica.viewmodel.UsersViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val VIEWMODELModule = module {

    viewModel { UsersViewModel() }
    viewModel { LanguageViewModel() }


}