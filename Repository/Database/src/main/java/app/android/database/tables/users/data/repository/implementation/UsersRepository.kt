package app.android.database.tables.users.data.repository.implementation

import app.android.database.domain.entities.UsersEntity
import app.android.database.tables.users.data.datasource.interfaces.IUsersDataSource
import app.android.database.tables.users.data.repository.interfaces.IUsersRepository
import kotlinx.coroutines.flow.Flow

class UsersRepository (
    private val dataSource: IUsersDataSource,
) : IUsersRepository {

    override fun getAll(): Flow<List<UsersEntity>> =
        dataSource.getAll()

    override fun get(id: Long): Flow<UsersEntity?> =
        dataSource.get(id)

    override suspend fun insert(users: UsersEntity): Long =
        dataSource.insert(users)

    override suspend fun update(users: UsersEntity): Int =
        dataSource.update(users)

    override suspend fun delete(id: Long): Int =
        dataSource.delete(id)

    override suspend fun deleteAll(): Int =
        dataSource.deleteAll()
}
