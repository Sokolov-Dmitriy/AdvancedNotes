package com.sokolovds.domain.exceptions

import com.sokolovds.domain.models.Result



suspend fun wrapRepositoryExceptionUnit(
    block: suspend () -> Unit
): Result<Int> = try {
    block()
    Result.Success(Result.ON_SUCCESS_DEFAULT)
} catch (e: AppException) {
    Result.Error(e)
} catch (e: Throwable) {
    e.printStackTrace()
    Result.Error(OtherException())
}


fun <T> wrapRepositoryExceptionFlow(
    block: () -> T
): Result<T> = try {
    Result.Success(block())
} catch (e: AppException) {
    Result.Error(e)
} catch (e: Throwable) {
    e.printStackTrace()
    Result.Error(OtherException())
}


