package com.ulascan.app.data.remote.response

import com.google.gson.annotations.SerializedName

data class HistoriesResponse(

	@field:SerializedName("data")
	val data: HistoriesData,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Boolean
)

data class HistoriesItem(

	@field:SerializedName("delivery")
	val delivery: Int,

	@field:SerializedName("admin_response")
	val adminResponse: Int,

	@field:SerializedName("positive_count")
	val positiveCount: Int,

	@field:SerializedName("product_condition")
	val productCondition: Any,

	@field:SerializedName("rating")
	val rating: Int,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("packaging")
	val packaging: Any,

	@field:SerializedName("product_name")
	val productName: String,

	@field:SerializedName("deleted_at")
	val deletedAt: Any,

	@field:SerializedName("url")
	val url: String,

	@field:SerializedName("content")
	val content: String,

	@field:SerializedName("ulasan")
	val ulasan: Int,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("user_id")
	val userId: String,

	@field:SerializedName("product_id")
	val productId: String,

	@field:SerializedName("negative_count")
	val negativeCount: Int,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("bintang")
	val bintang: Any
)

data class HistoriesData(

	@field:SerializedName("total")
	val total: Int,

	@field:SerializedName("pages")
	val pages: Int,

	@field:SerializedName("limit")
	val limit: Int,

	@field:SerializedName("histories")
	val histories: List<HistoriesItem>,

	@field:SerializedName("page")
	val page: Int
)
