package com.sokolovds.domain.models

import com.sokolovds.domain.exceptions.AppException

sealed class Result<out T> {
    companion object {
        const val ON_SUCCESS_DEFAULT = 1
    }

    data class Success<out R>(
        val value: R
    ) : Result<R>()

    data class Error(
        val exception: AppException
    ) : Result<Nothing>()
}


inline fun <reified T> Result<T>.onError(block: (exception: AppException) -> Unit): Result<T> {
    if (this is Result.Error) {
        block(exception)
    }
    return this
}

inline fun <reified T> Result<T>.onSuccess(block: (value: T) -> Unit): Result<T> {
    if (this is Result.Success) {
        block(value)
    }
    return this
}

inline fun <reified T> Result<T>.onFinal(block: () -> Unit): Result<T> {
    block()
    return this
}