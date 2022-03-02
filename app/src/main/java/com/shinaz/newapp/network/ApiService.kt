package com.shinaz.newapp.network

import com.shinaz.newapp.model.BaseResponse
import com.shinaz.newapp.model.Post
import com.shinaz.newapp.model.auth.LoginRequest
import com.shinaz.newapp.model.auth.LoginResponse
import com.shinaz.newapp.model.auth.SignUpReq
import com.shinaz.newapp.model.profile.ProfileResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST

interface ApiService {

    @GET("posts")
    suspend fun getPost():List<Post>

    @POST("login")
    suspend fun login(@HeaderMap headers: Map<String, String>, @Body request: LoginRequest): LoginResponse

    @POST("verifyEmail")
    suspend fun verifyEmail(@HeaderMap headers: Map<String, String>, @Body request: SignUpReq): BaseResponse

    @POST("register")
    suspend fun register(@HeaderMap headers: Map<String, String>, @Body request: SignUpReq): BaseResponse

    @GET("profile")
    suspend fun profile(@HeaderMap headers: Map<String, String>): ProfileResponse

}