package com.ragvax.dictionary.utils

sealed class Resource<T>(val data: T?, val title: String?, val msg: String?) {
    class Success<T>(data: T) : Resource<T>(data, null, null)
    class ErrorGeneric<T>(title: String?, message: String) : Resource<T>(null, title, message)
    class Error<T>(message: String?) : Resource<T>(null, null, message)
}
