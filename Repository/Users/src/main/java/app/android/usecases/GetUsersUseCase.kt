package app.android.usecases

import app.android.network.data.ApiResult
import app.android.network.data.User
import app.android.network.networking.ApiService

class GetUsersUseCase(private val apiService: ApiService) {

    suspend fun getUsers(numberOfUsers: Int): ApiResult<List<User>> {
        return try {
            val response = apiService.getUsers(numberOfUsers)
            if (response.isSuccessful) {
                val users = response.body()?.results
                if (users != null) {
                    ApiResult.Success(users)
                } else {
                    ApiResult.Error(Exception("Null response"))
                }
            } else {
                val errorBody = response.errorBody()?.string() ?: "Error de red"
                ApiResult.Error(Exception("Failed response: $errorBody"))
            }
        } catch (e: Exception) {
            ApiResult.Error(e)
        }
    }
}
