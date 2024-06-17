package com.ulascan.app.data.remote.api

import com.ulascan.app.BuildConfig
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
  companion object {
    var BASE_URL: String = BuildConfig.API_BASE_URL

    fun getApiService(token: String?): ApiService {
      val loggingInterceptor =
          if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
          } else {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
          }

      val authInterceptor = AuthInterceptor(token)

      val client =
          OkHttpClient.Builder()
              .connectTimeout(5, TimeUnit.MINUTES)
              .writeTimeout(5, TimeUnit.MINUTES)
              .readTimeout(5, TimeUnit.MINUTES)
              .addInterceptor(loggingInterceptor)
              .addInterceptor(authInterceptor)
              .build()

      val retrofit =
          Retrofit.Builder()
              .baseUrl(BASE_URL)
              .addConverterFactory(GsonConverterFactory.create())
              .client(client)
              .build()
      return retrofit.create(ApiService::class.java)
    }
  }
}
