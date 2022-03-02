package com.shinaz.newapp.model.auth

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.shinaz.newapp.model.BaseResponse

class LoginResponse: BaseResponse() {
    @SerializedName("token")
    @Expose
    var token: String? = null
}