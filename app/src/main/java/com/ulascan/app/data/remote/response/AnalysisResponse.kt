package com.ulascan.app.data.remote.response

import com.google.gson.annotations.SerializedName

data class Chat(
    val messages: List<Message>
) {
    data class Message(
        val text: String,
        val response: AnalysisData? = null,
        val isResponse: Boolean,
    )
}

data class AnalysisResponse(

    @field:SerializedName("data")
    val data: AnalysisData? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("status")
    val status: Boolean? = null
)

data class AnalysisData(

    @field:SerializedName("count_positive")
    val countPositive: Int? = null,

    @field:SerializedName("summary")
    val summary: String? = null,

    @field:SerializedName("delivery")
    val delivery: Any? = null,

    @field:SerializedName("admin_response")
    val adminResponse: Any? = null,

    @field:SerializedName("product_condition")
    val productCondition: Any? = null,

    @field:SerializedName("rating")
    val rating: Any? = null,

    @field:SerializedName("image_urls")
    val imageUrls: List<String?>? = null,

    @field:SerializedName("packaging")
    val packaging: Any? = null,

    @field:SerializedName("shop_name")
    val shopName: String? = null,

    @field:SerializedName("product_name")
    val productName: String? = null,

    @field:SerializedName("ulasan")
    val ulasan: Any? = null,

    @field:SerializedName("count_negative")
    val countNegative: Int? = null,

    @field:SerializedName("product_description")
    val productDescription: String? = null,

    @field:SerializedName("bintang")
    val bintang: Any? = null
)
