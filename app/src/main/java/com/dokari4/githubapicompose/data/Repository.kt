package com.dokari4.githubapicompose.data

import android.util.Log
import com.dokari4.githubapicompose.data.remote.dto.DetailUserDto
import com.dokari4.githubapicompose.data.remote.network.ApiResponse
import com.dokari4.githubapicompose.data.remote.network.ApiService
import com.dokari4.githubapicompose.data.remote.network.RetrofitInstance
import com.dokari4.githubapicompose.data.remote.dto.UserDto

class Repository {
    private val apiService: ApiService = RetrofitInstance.api

    suspend fun getUsers(): ApiResponse<List<UserDto>> {
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

    suspend fun fetchDetailUser(username: String): ApiResponse<DetailUserDto> {
         try {
            val response = apiService.getUserDetail(username)
            return ApiResponse.Success(response)
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("Repository", "getDetailUser: ${e.message}")
            return ApiResponse.Error(e.message.toString())
        }
    }
}