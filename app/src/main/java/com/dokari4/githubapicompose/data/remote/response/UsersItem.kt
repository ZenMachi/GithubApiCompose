package com.dokari4.githubapicompose.data.remote.response

import com.google.gson.annotations.SerializedName

data class UsersItem(
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("avatar_url")
    val avatar: String,
    @field:SerializedName("login")
    val idName: String,
    @field:SerializedName("url")
    val url: String
)