package com.shinaz.newapp.ui.auth.login

import com.shinaz.newapp.ui.auth.login.LoggedInUserView

/**
 * Authentication result : success (user details) or error message.
 */
data class LoginResult(
    val success: LoggedInUserView? = null,
    val error: Int? = null
)