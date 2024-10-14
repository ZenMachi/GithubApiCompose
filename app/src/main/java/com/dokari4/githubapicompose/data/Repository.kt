package com.dokari4.githubapicompose.data

import android.util.Log
import com.dokari4.githubapicompose.data.remote.network.ApiResponse
import com.dokari4.githubapicompose.data.remote.network.ApiService
import com.dokari4.githubapicompose.data.remote.network.RetrofitInstance
import com.dokari4.githubapicompose.data.remote.response.UsersItem

class Repository {
    private val apiService: ApiService = RetrofitInstance.api

    suspend fun getUsers(): ApiResponse<List<UsersItem>> {
         try {
            val response = apiService.getUsers()
             return if (response.isNotEmpty()) {
                 ApiResponse.Success(response)
             } else {
                 ApiResponse.Empty
             }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("Repository", "getUsers: ${e.message}")
            return ApiResponse.Error(e.message.toString())
        }
    }
}