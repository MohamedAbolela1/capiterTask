package com.example.taskcapiter.base

import androidx.annotation.StringRes

sealed class Status<out T> {
    object Loading : Status<Nothing>()
    data class Success<T>(
        val data: T? = null,
        @StringRes val message: Int = STATUS_NO_MESSAGE
    ) : Status<T>()

    data class Error(val errorTypes: ErrorTypes) : Status<Nothing>()

    companion object {
        const val STATUS_NO_MESSAGE = -1
        const val STATUS_NO_SERVER_MESSAGE = ""
    }
}