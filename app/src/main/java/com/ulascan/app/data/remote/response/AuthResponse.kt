package com.ulascan.app.data.remote.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @field:SerializedName("data") val data: LoginData? = null,
    @field:SerializedName("message") val message: String? = null,
    @field:SerializedName("status") val status: Boolean? = null
)

data class LoginData(
    @field:SerializedName("role") val role: String? = null,
    @field:SerializedName("token") val token: String? = null
)

data class RegisterResponse(
    @field:SerializedName("data") val data: RegisterData? = null,
    @field:SerializedName("message") val message: String? = null,
    @field:SerializedName("status") val status: Boolean? = null
)

data class RegisterData(
    @field:SerializedName("role") val role: String? = null,
    @field:SerializedName("name") val name: String? = null,
    @field:SerializedName("id") val id: String? = null,
    @field:SerializedName("email") val email: String? = null
)

data class UserResponse(
    @field:SerializedName("data") val data: UserData,
    @field:SerializedName("message") val message: String,
    @field:SerializedName("status") val status: Boolean
)

data class UserData(
    @field:SerializedName("role") val role: String,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("id") val id: String,
    @field:SerializedName("email") val email: String
)
