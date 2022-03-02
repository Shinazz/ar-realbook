package com.shinaz.newapp.model.auth

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SignUpReq(
    @SerializedName("email")
    @Expose
    var email: String? = null,

    @SerializedName("password")
    @Expose
    var password: String? = null,


    @SerializedName("token")
    @Expose
    var token: String? = null,

    @SerializedName("userName")
    @Expose
    var userName: String? = null
) {

}
