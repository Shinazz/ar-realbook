package com.shinaz.newapp.ui.auth.signup

import com.shinaz.newapp.ui.auth.login.LoginResult

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import androidx.lifecycle.viewModelScope
import com.shinaz.newapp.model.auth.LoginResponse
import com.shinaz.base.util.ApiState
import com.shinaz.newapp.model.BaseResponse
import com.shinaz.newapp.model.auth.SignUpReq
import com.shinaz.newapp.ui.auth.data.LoginRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class SIgnUpViewModel
@Inject
constructor(private val loginRepository: LoginRepository) : ViewModel() {


    private val verifyEmailStateFlow: MutableStateFlow<ApiState<BaseResponse>>
            = MutableStateFlow(ApiState.Empty)

    val _verifyEmailStateFlow: StateFlow<ApiState<
            BaseResponse>> = verifyEmailStateFlow
    fun verifyEmail(req: SignUpReq) = viewModelScope.launch {
        verifyEmailStateFlow.value = ApiState.Loading
        loginRepository.verifyEmail(req)
            .catch { e->
                verifyEmailStateFlow.value= ApiState.Failure(e)
            }.collect { data ->
                verifyEmailStateFlow.value= ApiState.Success(data)
            }
    }


    private val registerStateFlow: MutableStateFlow<ApiState<BaseResponse>>
            = MutableStateFlow(ApiState.Empty)

    val _registerEmailStateFlow: StateFlow<ApiState<
            BaseResponse>> = verifyEmailStateFlow
    fun register(req: SignUpReq) = viewModelScope.launch {
        registerStateFlow.value = ApiState.Loading
        loginRepository.register(req)
            .catch { e->
                registerStateFlow.value= ApiState.Failure(e)
            }.collect { data ->
                registerStateFlow.value= ApiState.Success(data)
            }
    }


}