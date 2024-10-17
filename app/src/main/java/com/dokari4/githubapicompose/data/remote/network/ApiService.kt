package com.dokari4.githubapicompose.data.remote.network

import com.dokari4.githubapicompose.data.remote.dto.DetailUserDto
import com.dokari4.githubapicompose.data.remote.dto.UserDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("users")
    suspend fun getUsers(): List<UserDto>

    @GET("users/{username}")
    suspend fun getUserDetail(@Path("username") username: String): DetailUserDto

    @GET("users/{username}/followers")
    suspend fun getFollowers(@Path("username") username: String): List<UserDto>

    @GET("users/{username}/following")
    suspend fun getFollowing(@Path("username") username: String): List<UserDto>
}