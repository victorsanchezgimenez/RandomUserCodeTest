package app.android.database.tables.users.data.repository.interfaces

import app.android.database.domain.entities.UsersEntity
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for managing Users data.
 */
interface IUsersRepository {

    /**
     * Retrieves all users from the repository.
     *
     * @return A flow that emits a list of all users.
     */
    fun getAll(): Flow<List<UsersEntity>>

    /**
     * Retrieves a specific user by its unique identifier.
     *
     * @param id The unique identifier of the user.
     * @return A flow that emits the specified user, or null if not found.
     */
    fun get(id: Long): Flow<UsersEntity?>

    /**
     * Inserts a new user into the repository.
     *
     * @param users The details of the user to be inserted.
     * @return The unique identifier for the inserted user.
     */
    suspend fun insert(users: UsersEntity): Long

    /**
     * Updates the details of an existing user in the repository.
     *
     * @param users The updated details of the user.
     * @return The number of rows affected (typically 1 if successful, 0 otherwise).
     */
    suspend fun update(users: UsersEntity): Int

    /**
     * Deletes a specific user by its unique identifier.
     *
     * @param id The unique identifier of the user to be deleted.
     * @return The number of rows affected (typically 1 if successful, 0 otherwise).
     */
    suspend fun delete(id: Long): Int

    /**
     * Deletes all users from the repository.
     *
     * @return The number of rows affected.
     */
    suspend fun deleteAll(): Int

}