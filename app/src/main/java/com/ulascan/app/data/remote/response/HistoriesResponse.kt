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


data class HistoriesItem(
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

	@field:SerializedName("packaging")
	val packaging: Double,

	@field:SerializedName("product_name")
	val productName: String,
	
	@field:SerializedName("ulasan")
	val ulasan: Int,

	@field:SerializedName("count_negative")
	val countNegative: Int,
	
	@field:SerializedName("bintang")
	val bintang: Double,

	@field:SerializedName("product_condition")
	val productCondition: Double,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("deleted_at")
	val deletedAt: Any?,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("url")
	val url: String,

	@field:SerializedName("user_id")
	val userId: String,

	@field:SerializedName("product_id")
	val productId: String,

	@field:SerializedName("id")
	val id: String,
)