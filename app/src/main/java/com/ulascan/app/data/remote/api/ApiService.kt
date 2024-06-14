package com.ulascan.app.data.remote.api

import com.ulascan.app.NavigationItem
import com.ulascan.app.data.remote.response.AnalysisResponse
import com.ulascan.app.data.remote.response.LoginResponse
import com.ulascan.app.data.remote.response.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

data class RegisterRequest(val name: String, val email: String, val password: String)
data class LoginRequest(val email: String, val password: String)
interface ApiService {
    @GET("ml/guest/analysis")
    suspend fun getGuestAnalysis(
        @Query("product_url") productUrl: String
    ): AnalysisResponse

    @GET("ml/analysis")
    suspend fun getAnalysis(
        @Query("product_url") productUrl: String
    ): AnalysisResponse

    @POST("user")
    suspend fun register(@Body request: RegisterRequest): RegisterResponse

    @POST("user/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse
}