package app.android.network

import app.android.network.data.ApiResponse
import app.android.network.data.ApiResult
import app.android.network.data.Coordinates
import app.android.network.data.Dob
import app.android.network.data.ID
import app.android.network.data.Info
import app.android.network.data.Location
import app.android.network.data.Login
import app.android.network.data.Name
import app.android.network.data.Picture
import app.android.network.data.Registered
import app.android.network.data.Street
import app.android.network.data.Timezone
import app.android.network.data.User
import app.android.network.networking.ApiService
import app.android.usecases.GetUsersUseCase
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class GetUsersUseCaseTest {

    private val apiService = mockk<ApiService>()
    private lateinit var getUsersUseCase: GetUsersUseCase

    @Before
    fun setUp() {
        getUsersUseCase = GetUsersUseCase(apiService)
    }

    @Test
    fun `getUsers when successful response`() = runBlocking {
        val user = createUser()
        val info = Info("random_seed", 1, 1, "1.0")
        val response = Response.success(ApiResponse(listOf(user), info))

        coEvery { apiService.getUsers(any()) } returns response

        val result = getUsersUseCase.getUsers(1)

        TestCase.assertTrue(result is ApiResult.Success)

        val users = (result as ApiResult.Success).data
        TestCase.assertEquals(1, users.size)
    }

    @Test
    fun `getUsers when response is null`() = runBlocking {
        val response = Response.success<ApiResponse>(null)
        coEvery { apiService.getUsers(any()) } returns response

        val result = getUsersUseCase.getUsers(1)

        TestCase.assertTrue(result is ApiResult.Error)

        val error = (result as ApiResult.Error).exception
        TestCase.assertNotNull(error)
        TestCase.assertEquals("Null response", error.message)
    }

    // Add more tests for other cases, such as failed responses or exceptions
    @Test
    fun `getUsers when response is unsuccessful`() = runBlocking {
        val response = Response.error<ApiResponse>(400, ResponseBody.create(null, "Bad Request"))
        coEvery { apiService.getUsers(any()) } returns response

        val result = getUsersUseCase.getUsers(1)

        TestCase.assertTrue(result is ApiResult.Error)

        val error = (result as ApiResult.Error).exception
        TestCase.assertNotNull(error)
        TestCase.assertEquals("Failed response: Bad Request", error.message)
    }

    // Create and return a user with fictitious data
    private fun createUser(): User {
        return User(
            gender = "male",
            name = Name("Mr", "John", "Doe"),
            location = Location(
                Street(123, "Main St"),
                city = "New York",
                state = "NY",
                country = "USA",
                postcode = 0,
                coordinates = Coordinates("40.7128", "-74.0060"),
                timezone = Timezone("-5:00", "Eastern Standard Time")
            ),
            email = "johndoe@example.com",
            login = Login("123456789", "john", "password", "salt", "md5", "sha1", "sha256"),
            dob = Dob("1990-01-01", 32),
            registered = Registered("2020-01-01", 2),
            phone = "123-456-7890",
            cell = "987-654-3210",
            id = ID("1234567890", null),
            picture = Picture("large_url", "medium_url", "thumbnail_url"),
            nat = "US"
        )
    }
}
