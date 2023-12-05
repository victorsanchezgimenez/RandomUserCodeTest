package app.android.database

import androidx.room.Database
import androidx.room.RoomDatabase
import app.android.database.dao.UserDetailsDao
import app.android.database.domain.UserDetailsEntity

@Database(entities = [UserDetailsEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun userDetailsDao(): UserDetailsDao
}