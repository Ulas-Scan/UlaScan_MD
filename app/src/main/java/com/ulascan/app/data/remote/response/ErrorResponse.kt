package com.ulascan.app.data.remote.response

import com.google.gson.annotations.SerializedName

data class ErrorResponse(

    @field:SerializedName("data")
    val data: Data? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("error")
    val error: String? = null,

    @field:SerializedName("status")
    val status: Boolean? = null
)

data class Data(
    val any: Any? = null
)
