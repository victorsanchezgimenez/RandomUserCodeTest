package app.android.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import app.android.database.domain.UserDetailsEntity
import app.android.database.utils.Constants.TABLE_USER_DETAILS
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDetailsDao {

    @Query("SELECT * FROM $TABLE_USER_DETAILS ORDER BY id DESC")
    fun getAll(): Flow<List<UserDetailsEntity>>

    @Query("SELECT * FROM $TABLE_USER_DETAILS WHERE id = :id")
    fun get(id: Long): Flow<UserDetailsEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<UserDetailsEntity>): List<Long> // Cambia el tipo de retorno a List<Long>

    @Update
    fun update(users: UserDetailsEntity): Int

    @Query("DELETE FROM $TABLE_USER_DETAILS WHERE id = :id")
    fun delete(id: Long): Int

    @Query("DELETE FROM $TABLE_USER_DETAILS")
    suspend fun deleteAll(): Int
}