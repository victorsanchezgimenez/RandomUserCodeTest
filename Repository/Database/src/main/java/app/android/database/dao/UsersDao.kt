package app.android.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import app.android.database.domain.entities.UsersEntity
import app.android.database.utils.Constants.TABLE_USERS
import kotlinx.coroutines.flow.Flow

@Dao
interface UsersDao {

    @Query("SELECT * FROM $TABLE_USERS ORDER BY id DESC")
    fun getAll(): Flow<List<UsersEntity>>

    @Query("SELECT * FROM $TABLE_USERS WHERE id = :id")
    fun get(id: Long): Flow<UsersEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userEntity: UsersEntity): Long

    @Update
    suspend fun update(userEntity: UsersEntity): Int

    @Query("DELETE FROM $TABLE_USERS WHERE id = :id")
    suspend fun delete(id: Long): Int

    @Query("DELETE FROM $TABLE_USERS")
    suspend fun deleteAll(): Int

}
