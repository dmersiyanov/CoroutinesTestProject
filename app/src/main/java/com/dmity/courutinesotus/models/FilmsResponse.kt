package com.dmity.courutinesotus.models

import com.google.gson.annotations.SerializedName

data class FilmsResponse(

    @field:SerializedName("page")
    val page: Int? = null,

    @field:SerializedName("total_pages")
    val totalPages: Int? = null,

    @field:SerializedName("results")
    val results: List<MovieDTO?>? = null,

    @field:SerializedName("total_results")
    val totalResults: Int? = null
)