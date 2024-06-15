package com.ulascan.app.data.repository

import com.ulascan.app.data.remote.response.AnalysisResponse
import com.ulascan.app.data.states.ResultState

interface ChatRepository {
    suspend fun getAnalysis(productUrl: String): ResultState<AnalysisResponse>
}