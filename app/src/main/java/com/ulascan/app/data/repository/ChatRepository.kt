package com.ulascan.app.data.repository

import androidx.paging.PagingData
import com.ulascan.app.data.remote.response.AnalysisResponse
import com.ulascan.app.data.remote.response.HistoriesItem
import com.ulascan.app.data.remote.response.HistoriesResponse
import com.ulascan.app.data.states.ResultState
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    suspend fun getAnalysis(productUrl: String): ResultState<AnalysisResponse>
}

interface UserChatRepository: ChatRepository {
    suspend fun getHistory(): ResultState<HistoriesResponse>
    
    suspend fun getHistoryWithPaging(): Flow<PagingData<HistoriesItem>>
}