package com.example.taskcapiter.base

sealed class ErrorTypes(
    val errorTitle: String? = null,
    val errorSubTitle: String? = null,
    val errorIcon: Int? = null
) {
    object NoData : ErrorTypes("No data found")

    object NoNetwork : ErrorTypes("No network")

    object GeneralError :
        ErrorTypes("Something wrong")
}