package com.shinaz.newapp.model
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

class Message {
    @SerializedName("code")
    @Expose
    var code: String? = null

    @SerializedName("messageList")
    @Expose
    var messageList: List<MessageList> = ArrayList()
}