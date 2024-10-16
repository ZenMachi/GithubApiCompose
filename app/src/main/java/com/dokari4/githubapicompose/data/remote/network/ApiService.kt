package com.dokari4.githubapicompose.data.remote.network

import com.dokari4.githubapicompose.data.remote.dto.DetailUserDto
import com.dokari4.githubapicompose.data.remote.dto.UserDto
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ApiService {

    @Headers("Authorization: token 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")
    @GET("users")
    suspend fun getUsers(): List<UserDto>

    @Headers("Authorization: token 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")
    @GET("users/{username}")
    suspend fun getUserDetail(@Path("username") username: String): DetailUserDto

    @Headers("Authorization: token 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")
    @GET("users/{username}/followers")
    suspend fun getFollowers(@Path("username") username: String): List<UserDto>

    @Headers("Authorization: token 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")
    @GET("users/{username}/following")
    suspend fun getFollowing(@Path("username") username: String): List<UserDto>
}