package com.shinaz.newapp.model.profile

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProfileResponse(
        @SerializedName("coinsEarned")
        @Expose
        var coinsEarned: Int? = null,

        @SerializedName("email")
        @Expose
        var email: String? = null,

        @SerializedName("name")
        @Expose
        var name: String? = null
) {

}
