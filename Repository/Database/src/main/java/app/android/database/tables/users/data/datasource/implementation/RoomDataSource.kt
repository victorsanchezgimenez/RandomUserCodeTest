package app.android.database.tables.users.data.datasource.implementation

import app.android.database.dao.UsersDao
import app.android.database.domain.entities.UsersEntity
import app.android.database.tables.users.data.datasource.interfaces.IUsersDataSource
import kotlinx.coroutines.flow.Flow

class RoomDataSource(
    private val usersDao: UsersDao
) : IUsersDataSource {

    override fun getAll(): Flow<List<UsersEntity>> {
        return usersDao.getAll()
    }

    override fun get(id: Long): Flow<UsersEntity?> {
        return usersDao.get(id)
    }

    override suspend fun insert(users: UsersEntity): Long {
        return usersDao.insert(users)
    }

    override suspend fun update(users: UsersEntity): Int {
        return usersDao.update(users)
    }

    override suspend fun delete(id: Long): Int {
        return usersDao.delete(id)
    }

    override suspend fun deleteAll(): Int {
        return usersDao.deleteAll()
    }

}