package com.shinaz.newapp.network
import com.shinaz.newapp.model.BaseResponse
import com.shinaz.newapp.model.Post
import com.shinaz.newapp.model.auth.LoginRequest
import com.shinaz.newapp.model.auth.LoginResponse
import com.shinaz.newapp.model.auth.SignUpReq
import com.shinaz.newapp.model.profile.ProfileResponse
import javax.inject.Inject

class ApiServiceImpl @Inject constructor(private val apiService: ApiService) {

    suspend fun getPost():List<Post> = apiService.getPost()
    suspend fun login(loginRequest: LoginRequest): LoginResponse = apiService.login(Util.formHeader("auth"), loginRequest)
    suspend fun profile(): ProfileResponse = apiService.profile(Util.formHeader())
    suspend fun verifyEmail(req: SignUpReq): BaseResponse = apiService.verifyEmail(Util.formHeader("auth"), req)
    suspend fun register(req: SignUpReq): BaseResponse = apiService.register(Util.formHeader("auth"), req)

}