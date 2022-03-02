package com.shinaz.newapp.ui.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import androidx.lifecycle.viewModelScope
import com.shinaz.newapp.model.auth.LoginResponse
import com.shinaz.base.util.ApiState
import com.shinaz.newapp.ui.auth.data.LoginRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel
@Inject
constructor(private val loginRepository: LoginRepository) : ViewModel() {

//    private val _loginForm = MutableLiveData<LoginFormState>()
//    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

//    fun login(username: String, password: String) {
//        // can be launched in a separate asynchronous job
//        val result = loginRepository.login(username, password)
//
//        if (result is Result.Success) {
//            _loginResult.value =
//                LoginResult(success = LoggedInUserView(displayName = result.data.displayName))
//        } else {
//            _loginResult.value = LoginResult(error = R.string.login_failed)
//        }
//    }

    private val loginStateFlow: MutableStateFlow<ApiState<LoginResponse>>
            = MutableStateFlow(ApiState.Empty)

    val _loginStateFlow: StateFlow<ApiState<
            LoginResponse>> = loginStateFlow
    fun login(username: String, password: String) = viewModelScope.launch {
        loginStateFlow.value = ApiState.Loading
        loginRepository.login(username, password)
            .catch { e->
                loginStateFlow.value= ApiState.Failure(e)
            }.collect { data ->
                loginStateFlow.value= ApiState.Success(data)
            }
    }


    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
//            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
//            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
//            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains("@")) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}