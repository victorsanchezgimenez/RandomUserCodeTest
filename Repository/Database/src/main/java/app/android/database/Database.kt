package app.android.database

import androidx.room.Database
import androidx.room.RoomDatabase
import app.android.database.dao.UsersDao
import app.android.database.domain.entities.UsersEntity

@Database(entities = [
    UsersEntity::class,
], version = 1, exportSchema = true)
abstract class Database : RoomDatabase() {
    abstract fun getUsersDao(): UsersDao
}