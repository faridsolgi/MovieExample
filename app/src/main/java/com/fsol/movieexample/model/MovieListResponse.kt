package com.fsol.movieexample.model

import com.google.gson.annotations.SerializedName

data class MovieListResponse(

	@field:SerializedName("metadata")
	val metadata: Metadata? = null,

	@field:SerializedName("data")
	val data: List<DataItem?>? = null
)

data class Metadata(

	@field:SerializedName("per_page")
	val perPage: Int? = null,

	@field:SerializedName("total_count")
	val totalCount: Int? = null,

	@field:SerializedName("current_page")
	val currentPage: String? = null,

	@field:SerializedName("page_count")
	val pageCount: Int? = null
)

data class DataItem(

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("images")
	val images: List<String?>? = null,

	@field:SerializedName("year")
	val year: String? = null,

	@field:SerializedName("genres")
	val genres: List<String?>? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("imdb_rating")
	val imdbRating: String? = null,

	@field:SerializedName("poster")
	val poster: String? = null
)
