package com.shinaz.newapp.model
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MessageList {
    @SerializedName("errorCode")
    @Expose
    var errorCode: String? = null

    @SerializedName("text")
    @Expose
    var text: String? = null
}