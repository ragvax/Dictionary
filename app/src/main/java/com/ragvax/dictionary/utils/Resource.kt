package com.ragvax.dictionary.utils

sealed class Resource<out T> {
    data class Success<T>(val data: T): Resource<T>()
    data class Error<T>(val title: String, val message: String): Resource<T>()
    object Loading: Resource<Nothing>()
}
