package com.ulascan.app.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

data class Chat(
    val messages: List<Message>
) {
    data class Message(
        val text: String,
        val response: AnalysisData? = null,
        val isResponse: Boolean,
        val isError: Boolean = false
    )
}

data class AnalysisResponse(

    @field:SerializedName("data")
    val data: AnalysisData,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("status")
    val status: Boolean
)

@Serializable
@Parcelize
data class AnalysisData(

    @field:SerializedName("count_positive")
    val countPositive: Int,

    @field:SerializedName("summary")
    val summary: String,

    @field:SerializedName("delivery")
    val delivery: Double,

    @field:SerializedName("admin_response")
    val adminResponse: Double,
    
    @field:SerializedName("rating")
    val rating: Int,

    @field:SerializedName("image_urls")
    val imageUrls: List<String>,

    @field:SerializedName("packaging")
    val packaging: Double,

    @field:SerializedName("shop_name")
    val shopName: String,

    @field:SerializedName("product_name")
    val productName: String,

    @field:SerializedName("ulasan")
    val ulasan: Int,

    @field:SerializedName("count_negative")
    val countNegative: Int,

    @field:SerializedName("product_description")
    val productDescription: String,

    @field:SerializedName("bintang")
    val bintang: Double,

    @field:SerializedName("product_condition")
    val productCondition: Double,
) : Parcelable
