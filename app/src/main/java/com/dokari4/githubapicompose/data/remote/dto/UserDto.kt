package com.dokari4.githubapicompose.data.remote.dto

import com.google.gson.annotations.SerializedName

data class UserDto(
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("avatar_url")
    val avatarUrl: String,
    @field:SerializedName("login")
    val login: String,
    @field:SerializedName("url")
    val url: String
)