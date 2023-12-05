package app.android

import android.util.Log
import app.android.network.data.ApiResult
import app.android.network.data.User
import app.android.usecases.GetUsersUseCase

class UserHandler(private val getUsersUseCase: GetUsersUseCase) : IUserHandler {

    override suspend fun getUsers(numberOfUsers: Int): ApiResult<List<User>> {
        Log.d("UserHandler", "getUsers llamado con $numberOfUsers")
        return getUsersUseCase.getUsers(numberOfUsers)
    }
}
