package com.shinaz.newapp.repository

import com.shinaz.newapp.model.Post
import com.shinaz.newapp.model.auth.LoginRequest
import com.shinaz.newapp.model.auth.LoginResponse
import com.shinaz.newapp.model.profile.ProfileResponse
import com.shinaz.newapp.network.ApiServiceImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainRepository
@Inject
constructor(private val apiServiceImpl: ApiServiceImpl) {

    fun getPost():Flow<List<Post>> = flow {
        emit(apiServiceImpl.getPost())
    }.flowOn(Dispatchers.IO)

    fun profile(): Flow<ProfileResponse> = flow {
        emit(apiServiceImpl.profile())
    }.flowOn(Dispatchers.IO)


}