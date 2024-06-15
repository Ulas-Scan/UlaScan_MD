package com.ulascan.app.data.remote.api

import com.ulascan.app.data.remote.request.LoginRequest
import com.ulascan.app.data.remote.request.RegisterRequest
import com.ulascan.app.data.remote.response.AnalysisResponse
import com.ulascan.app.data.remote.response.HistoriesResponse
import com.ulascan.app.data.remote.response.LoginResponse
import com.ulascan.app.data.remote.response.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @GET("ml/guest/analysis")
    suspend fun getGuestAnalysis(
        @Query("product_url") productUrl: String
    ): AnalysisResponse

    @GET("ml/analysis")
    suspend fun getAnalysis(
        @Query("product_url") productUrl: String
    ): AnalysisResponse
    
    
    @GET("history")
    suspend fun getHistories(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 10,
        @Query("product_name") productName: String = ""
    ): HistoriesResponse
    
    @Headers("isPublic: true")
    @POST("user")
    suspend fun register(@Body request: RegisterRequest): RegisterResponse

    @Headers("isPublic: true")
    @POST("user/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse
}