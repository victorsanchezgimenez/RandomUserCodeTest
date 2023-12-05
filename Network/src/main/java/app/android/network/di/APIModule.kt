package app.android.network.di

import app.android.network.core.RetrofitClient
import org.koin.dsl.module

val APIModule = module {

    single { RetrofitClient.api }
}