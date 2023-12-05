package app.android

import app.android.network.data.ApiResult
import app.android.network.data.User

interface IUserHandler {
    suspend fun getUsers(numberOfUsers: Int): ApiResult<List<User>>
}