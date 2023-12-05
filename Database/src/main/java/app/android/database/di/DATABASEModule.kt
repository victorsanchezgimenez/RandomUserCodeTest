package app.android.database.di

import androidx.room.Room
import app.android.database.AppDataBase
import app.android.database.utils.Constants
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val DATABASEModule = module {
    single {
        Room.databaseBuilder(androidContext(), AppDataBase::class.java, Constants.robotics_database)
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<AppDataBase>().userDetailsDao() }
}
