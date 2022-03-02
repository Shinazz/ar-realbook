package com.shinaz.newapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class BaseResponse {
    @SerializedName("message")
    @Expose
    var message: Message? = null
}