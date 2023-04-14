package com.example.rinjin.data.remote

import Item
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchUsersResultDto(
    @Json(name = "incomplete_results")
    val incompleteResults: Boolean?,
    val items: List<Item?>?,
    @Json(name = "total_count")
    val totalCount: Int?,
)