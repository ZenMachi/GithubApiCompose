package com.dokari4.githubapicompose.data.remote.network

import com.dokari4.githubapicompose.data.remote.response.UsersItem
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<UsersItem>
}