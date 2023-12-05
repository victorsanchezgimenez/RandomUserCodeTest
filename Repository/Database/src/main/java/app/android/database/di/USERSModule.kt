package app.android.database.di

import app.android.database.tables.users.data.datasource.implementation.RoomDataSource
import app.android.database.tables.users.data.datasource.interfaces.IUsersDataSource
import app.android.database.tables.users.data.repository.implementation.UsersRepository
import app.android.database.tables.users.data.repository.interfaces.IUsersRepository
import org.koin.dsl.module

val usersModule = module {

    single<IUsersRepository> {
        UsersRepository(
            get()
        )
    }

    single<IUsersDataSource> { RoomDataSource(get()) }

}
