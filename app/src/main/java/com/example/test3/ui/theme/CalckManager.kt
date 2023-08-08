package com.example.test3.ui.theme

import kotlinx.coroutines.flow.MutableSharedFlow

interface CalckManager {
    val data: MutableSharedFlow<Resource<CalckResult>>
    fun reconnect()

    fun disconnect()

    fun startReceiving()

    fun closeConnection()
}

sealed class Resource<out T:Any> {
    data class Success<out T:Any> (val data:T):Resource<T>()
    data class Error(val errorMessage:String):Resource<Nothing>()
    data class Loading<out T:Any>(val data:T? = null, val message:String? = null):Resource<T>()
}
