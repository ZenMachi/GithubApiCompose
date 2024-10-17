package com.dokari4.githubapicompose.data.remote.dto

import com.google.gson.annotations.SerializedName

data class SearchUserDto(
    @field:SerializedName("total_count")
    val totalCount: String,
    @field:SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    @field:SerializedName("items")
    val items: List<UserDto>
)