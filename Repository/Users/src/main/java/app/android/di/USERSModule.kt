package app.android.di

import app.android.IUserHandler
import app.android.UserHandler
import app.android.usecases.GetUsersUseCase
import org.koin.dsl.module

val USERSModule = module {

    single<IUserHandler>() { UserHandler(get()) }
    single { GetUsersUseCase(get()) }

}
