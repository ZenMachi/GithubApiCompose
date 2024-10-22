package com.dokari4.githubapicompose.data.remote.dto

import com.google.gson.annotations.SerializedName

data class DetailUserDto(
    @field:SerializedName("avatar_url")
    val avatarUrl: String,
    @field:SerializedName("bio")
    val bio: String?,
    @field:SerializedName("created_at")
    val createdAt: String,
    @field:SerializedName("followers")
    val followers: Int,
    @field:SerializedName("following")
    val following: Int,
    @field:SerializedName("html_url")
    val htmlUrl: String,
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("location")
    val location: String,
    @field:SerializedName("login")
    val login: String,
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("public_repos")
    val publicRepo: Int,
    @field:SerializedName("updated_at")
    val updatedAt: String,
)