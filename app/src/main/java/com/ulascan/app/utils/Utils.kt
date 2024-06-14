package com.ulascan.app.utils

import com.google.gson.Gson
import com.ulascan.app.data.remote.response.ErrorResponse
import retrofit2.HttpException
import java.util.regex.Pattern

val urlPattern = Pattern.compile(
    "^https:\\/\\/www\\.tokopedia\\.com\\/[a-zA-Z0-9\\-]+\\/[a-zA-Z0-9\\-]+(?:\\?[a-zA-Z0-9=&]*)?\$",
    Pattern.CASE_INSENSITIVE
)

fun String.isUrl(): Boolean {
    return urlPattern.matcher(this).matches()
}

fun String.toCapitalize(): String {
    if (this.isEmpty()) return this

    return this.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase() else it.toString()
    }
}

fun HttpException.getErrorMessage(): String {
    val jsonInString = this.response()?.errorBody()?.string()
    val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
    val errorMessage = errorBody.message
    return errorMessage ?: "An error occurred, please try again in 5 minutes."
}