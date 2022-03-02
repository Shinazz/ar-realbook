package com.shinaz.newapp.model.auth

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LoginRsp {
    @SerializedName("token")
    @Expose
    private val token: String? = null
}