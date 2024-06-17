package com.ulascan.app.utils

import com.google.gson.Gson
import com.ulascan.app.data.remote.response.ErrorResponse
import java.util.regex.Pattern
import retrofit2.HttpException

val urlPattern =
    Pattern.compile(
        "^https:\\/\\/www\\.tokopedia\\.com\\/[a-zA-Z0-9\\-]+\\/[a-zA-Z0-9\\-]+(?:\\?[a-zA-Z0-9=&]*)?\$",
        Pattern.CASE_INSENSITIVE)

fun String.isUrl(): Boolean {
  return urlPattern.matcher(this).matches()
}

fun String.toCapitalize(): String {
  if (this.isEmpty()) return this

  return this.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
}

inline fun <reified T> Any.castTo(): T {
  return when (T::class) {
    Int::class -> (this as? Number)?.toInt() as? T
    Double::class -> (this as? Number)?.toDouble() as? T
    Float::class -> (this as? Number)?.toFloat() as? T
    Long::class -> (this as? Number)?.toLong() as? T
    Short::class -> (this as? Number)?.toShort() as? T
    Byte::class -> (this as? Number)?.toByte() as? T
    String::class -> this.toString() as? T
    else -> this as? T
  } ?: throw ClassCastException("Cannot cast to ${T::class.java.simpleName}")
}

fun Any.castToDoubleThenToInt(): Int {
  return try {
    val doubleValue = this.castTo<Double>()
    doubleValue.toInt()
  } catch (e: ClassCastException) {
    throw ClassCastException("Cannot cast to Double")
  }
}

fun HttpException.getErrorMessage(): String {
  val jsonInString = this.response()?.errorBody()?.string()
  val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)

  val errorMessage = errorBody.message
  return errorMessage ?: "An error occurred, please try again in 5 minutes."
}
