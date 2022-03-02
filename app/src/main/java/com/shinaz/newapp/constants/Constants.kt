package com.shinaz.base.constants

import android.os.Build

object Constants {
    const val DEBUG = "debug"

    //Network
    const val CONTENT_TYPE_KEY = "Content-Type"
    const val CONTENT_TYPE_VALUE = "application/json"
    const val AUTH_KEY = "Authorization"
    const val BEARER_KEY = "Bearer "
    const val REQ_ID_KEY = "Rqst-Id"
    const val CONNECTION_PROBLEM = -1
    const val DEVICE_TYPE = "ANDROID"
    const val APP_VERSION = "x-app-ver"
    val APP_VERSION_CODE = "ANDROID_V" + Build.VERSION.RELEASE
    const val PHYSICAL_CARD = "PC"
    const val VIRTUAL_CARD = "VC"
}
