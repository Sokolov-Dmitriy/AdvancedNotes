package com.sokolovds.myapplication.presentation.utils

import android.app.Application
import com.sokolovds.domain.exceptions.*
import com.sokolovds.myapplication.R

class MessageProvider(
    private val app: Application
) {

    fun getMessageByException(e: AppException, withButton: Boolean = false) = when (e) {
        is EmptyTitleException -> app.getString(R.string.emptyTitleException)
        is OtherException -> if (withButton) app.getString(R.string.otherException) else app.getString(
            R.string.tryRepeatException
        )
    }
}