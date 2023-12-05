package alisys.robotica.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.android.IUserHandler
import app.android.network.data.ApiResult
import app.android.network.data.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class UsersViewModel : ViewModel(), KoinComponent {

    private val userHandler: IUserHandler by inject()

    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users.asStateFlow()

    init {
        fetchAndLogUsers(10)
    }

    fun fetchAndLogUsers(numberOfUsers: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = userHandler.getUsers(numberOfUsers)
                when (result) {
                    is ApiResult.Success -> {
                        _users.value = result.data
                    }
                    is ApiResult.Error -> {
                        Log.e("UsersViewModel", "Error fetching users", result.exception)
                    }
                }
            } catch (e: Exception) {
                Log.e("UsersViewModel", "Error fetching users", e)
            }
        }
    }
}