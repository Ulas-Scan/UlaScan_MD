package com.ulascan.app.data.remote.api

import com.ulascan.app.data.remote.response.AnalysisResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("ml/guest/analysis")
    suspend fun getAnalysis(
        @Query("product_url") productUrl: String
    ): AnalysisResponse
}