package com.shinaz.base.util

sealed class ApiState<out T>{
    object Loading : ApiState<Nothing>()
    class Failure<out T>(val msg:Throwable) : ApiState<T>()
    class Success<out T>(val data: T) : ApiState<T>()
    object Empty : ApiState<Nothing>()
}
