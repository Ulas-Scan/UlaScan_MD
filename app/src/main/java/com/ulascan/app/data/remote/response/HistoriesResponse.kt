package com.ulascan.app.data.remote.response

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
import java.util.Date
import java.util.Locale
import java.util.TimeZone

data class HistoriesResponse(
    @field:SerializedName("data") val data: HistoriesData,
    @field:SerializedName("message") val message: String,
    @field:SerializedName("status") val status: Boolean
)

data class HistoriesData(
    @field:SerializedName("total") val total: Int,
    @field:SerializedName("pages") val pages: Int,
    @field:SerializedName("limit") val limit: Int,
    @field:SerializedName("histories") val histories: List<HistoriesItem>,
    @field:SerializedName("page") val page: Int
)

data class HistoriesItem(
    @field:SerializedName("count_positive") val countPositive: Int,
    @field:SerializedName("summary") val summary: String,
    @field:SerializedName("delivery") val delivery: Double,
    @field:SerializedName("admin_response") val adminResponse: Double,
    @field:SerializedName("rating") val rating: Int,
    @field:SerializedName("packaging") val packaging: Double,
    @field:SerializedName("product_name") val productName: String,
    @field:SerializedName("ulasan") val ulasan: Int,
    @field:SerializedName("count_negative") val countNegative: Int,
    @field:SerializedName("bintang") val bintang: Double,
    @field:SerializedName("product_condition") val productCondition: Double,
    @field:SerializedName("created_at") val createdAt: String,
    @field:SerializedName("deleted_at") val deletedAt: Any?,
    @field:SerializedName("updated_at") val updatedAt: String,
    @field:SerializedName("url") val url: String,
    @field:SerializedName("user_id") val userId: String,
    @field:SerializedName("product_id") val productId: String,
    @field:SerializedName("id") val id: String,
) {
  val instant: Date
    get() =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
          parseInstantApi26(updatedAt)
        } else {
          parseInstantLegacy(updatedAt)
        }

  @RequiresApi(Build.VERSION_CODES.O)
  private fun parseInstantApi26(timestamp: String): Date {
    return Date.from(Instant.parse(timestamp).atZone(ZoneId.of("Asia/Jakarta")).toInstant())
  }

  private fun parseInstantLegacy(timestamp: String): Date {
    return SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
        .apply { timeZone = TimeZone.getTimeZone("Asia/Jakarta") }
        .parse(timestamp)!!
  }
}
