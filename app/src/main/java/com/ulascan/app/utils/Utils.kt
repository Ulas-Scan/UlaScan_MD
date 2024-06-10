package com.ulascan.app.utils

import java.util.regex.Pattern

val urlPattern = Pattern.compile(
    "^(http|https|ftp)://[a-zA-Z0-9\\-\\.]+\\.[a-zA-Z]{2,3}(/\\S*)?$",
    Pattern.CASE_INSENSITIVE
)

fun String.isUrl(): Boolean {
    return urlPattern.matcher(this).matches()
}