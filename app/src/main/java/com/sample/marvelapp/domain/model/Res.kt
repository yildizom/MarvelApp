package com.sample.marvelapp.domain.model

sealed class Res<out T> {
    data class Success<T>(val data: T): Res<T>()
    data class Error(val message: String? = null): Res<Nothing>()
    object Loading: Res<Nothing>()
}