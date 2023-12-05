package app.android.database.di

import androidx.room.Room
import androidx.room.RoomDatabase
import app.android.database.Database
import app.android.database.utils.Constants.stradivarius_database
import app.android.database.utils.migrations.MIGRATION
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import java.util.concurrent.Executors

val DATABASEModule = module {

    includes(
        usersModule,
    )

    single {
        Room.databaseBuilder(
            androidApplication(),
            Database::class.java,
            stradivarius_database
        ).addMigrations(MIGRATION)
            .setJournalMode(RoomDatabase.JournalMode.WRITE_AHEAD_LOGGING)
            .setTransactionExecutor(Executors.newFixedThreadPool(4))
            .addCallback(object: RoomDatabase.Callback() {
            })
            .build()
    }

    single { get<Database>().getUsersDao() }


}
