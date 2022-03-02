package com.shinaz.newapp.ui.auth.data

import com.shinaz.newapp.model.BaseResponse
import com.shinaz.newapp.model.auth.LoginRequest
import com.shinaz.newapp.model.auth.LoginResponse
import com.shinaz.newapp.model.auth.SignUpReq
import com.shinaz.newapp.network.ApiServiceImpl
import com.shinaz.newapp.ui.auth.data.model.LoggedInUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository
@Inject
constructor(val apiServiceImpl: ApiServiceImpl) {

    // in-memory cache of the loggedInUser object
    var user: LoggedInUser? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
        user = null
    }

    fun logout() {
        user = null
    }

//    fun login(username: String, password: String): Result<LoggedInUser> {
//        // handle login
//        val result = dataSource.login(username, password)
//
//        if (result is Result.Success) {
//            setLoggedInUser(result.data)
//        }
//
//        return result
//    }


    fun login(username: String, password: String): Flow<LoginResponse> = flow {
        val loginRequest = LoginRequest(username, password)
        emit(apiServiceImpl.login(loginRequest))
    }.flowOn(Dispatchers.IO)

    fun verifyEmail(req: SignUpReq): Flow<BaseResponse> = flow {
        emit(apiServiceImpl.verifyEmail(req))
    }.flowOn(Dispatchers.IO)

    fun register(req: SignUpReq): Flow<BaseResponse> = flow {
        emit(apiServiceImpl.register(req))
    }.flowOn(Dispatchers.IO)



    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
        this.user = loggedInUser
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}