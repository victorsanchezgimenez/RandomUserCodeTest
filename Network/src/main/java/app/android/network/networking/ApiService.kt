package app.android.network.networking

import app.android.network.data.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/api")
    suspend fun getUsers(@Query("results") numberResult: Int): Response<ApiResponse>
}