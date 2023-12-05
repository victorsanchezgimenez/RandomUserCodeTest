package alisys.robotica.viewmodel

import app.android.IUserHandler
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
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class UsersViewModelTest {

    private lateinit var viewModel: UsersViewModel
    private val userHandler: IUserHandler = mockk(relaxed = true)

    @Before
    fun setUp() {
        viewModel = UsersViewModel()
    }

    @Test
    fun `fetchAndLogUsers success scenario`() = runBlocking {
        // Mocking the user handler to return a list of users
        val mockUsers = listOf(mockUser1, mockUser2)
        val apiResponse = ApiResponse(mockUsers, Info("seed", 1, 1, "1.0"))
        coEvery { userHandler.getUsers(any()) } returns ApiResult.Success(apiResponse.results)

        // Calling the method in the ViewModel to fetch users
        viewModel.fetchAndLogUsers(10)

        // Collecting the emitted users from the ViewModel's users flow
        val emittedUsers = mutableListOf<List<User>>()
        val job = launch {
            viewModel.users.toList(emittedUsers)
        }

        // Introducing a delay to allow the flow to emit values
        delay(1000)

        // Asserting that the list of users is not empty and the first emission is not null
        assertTrue(emittedUsers.isNotEmpty(), "User list should not be empty.")
        assertNotNull(emittedUsers[0], "First emission of user flow should not be null.")

        // Cancelling the coroutine job
        job.cancel()
    }

    @Test
    fun `fetchAndLogUsers error scenario`() = runBlocking {
        // Mocking the user handler to return an error
        val exception = Exception("Error fetching users")
        coEvery { userHandler.getUsers(any()) } returns ApiResult.Error(exception)

        // Calling the method in the ViewModel to fetch users
        viewModel.fetchAndLogUsers(10)
    }


    private val mockUser1 = User(
        gender = "male",
        name = Name(title = "Mr", first = "John", last = "Doe"),
        location = Location(
            street = Street(number = 123, name = "Baker Street"),
            city = "London",
            state = "Greater London",
            country = "United Kingdom",
            postcode = "NW1 6XE",
            coordinates = Coordinates(latitude = "51.523767", longitude = "-0.1585557"),
            timezone = Timezone(offset = "+0:00", description = "Greenwich Mean Time")
        ),
        email = "john.doe@example.com",
        login = Login(
            uuid = "12345678-1234-1234-1234-123456789012",
            username = "johndoe",
            password = "password123",
            salt = "salt123",
            md5 = "md5123",
            sha1 = "sha1123",
            sha256 = "sha256123"
        ),
        dob = Dob(date = "1980-01-01T00:00:00Z", age = 42),
        registered = Registered(date = "2005-07-15T00:00:00Z", age = 17),
        phone = "0123456789",
        cell = "0987654321",
        id = ID(name = "SSN", value = "123-45-6789"),
        picture = Picture(
            large = "https://example.com/large.jpg",
            medium = "https://example.com/medium.jpg",
            thumbnail = "https://example.com/thumbnail.jpg"
        ),
        nat = "GB"
    )

    private val mockUser2 = User(
        gender = "female",
        name = Name(title = "Ms", first = "Jane", last = "Smith"),
        location = Location(
            street = Street(number = 789, name = "Oxford Street"),
            city = "Manchester",
            state = "Greater Manchester",
            country = "United Kingdom",
            postcode = "M1 4BT",
            coordinates = Coordinates(latitude = "53.4819", longitude = "-2.2374"),
            timezone = Timezone(offset = "+0:00", description = "British Summer Time")
        ),
        email = "jane.smith@example.com",
        login = Login(
            uuid = "87654321-4321-4321-4321-210987654321",
            username = "janesmith",
            password = "mypassword",
            salt = "salt987",
            md5 = "md5987",
            sha1 = "sha1987",
            sha256 = "sha256987"
        ),
        dob = Dob(date = "1990-05-15T00:00:00Z", age = 33),
        registered = Registered(date = "2010-09-20T00:00:00Z", age = 13),
        phone = "9876543210",
        cell = "0123456789",
        id = ID(name = "SSN", value = "987-65-4321"),
        picture = Picture(
            large = "https://example.com/large2.jpg",
            medium = "https://example.com/medium2.jpg",
            thumbnail = "https://example.com/thumbnail2.jpg"
        ),
        nat = "GB"
    )
}
