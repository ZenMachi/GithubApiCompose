package com.dokari4.githubapicompose.data

import android.util.Log
import com.dokari4.githubapicompose.data.remote.dto.DetailUserDto
import com.dokari4.githubapicompose.data.remote.network.ApiResponse
import com.dokari4.githubapicompose.data.remote.network.ApiService
import com.dokari4.githubapicompose.data.remote.network.RetrofitInstance
import com.dokari4.githubapicompose.data.remote.dto.UserDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class Repository {
    private val apiService: ApiService = RetrofitInstance.api

    /*
        All Exception
        Should be handled it on NetworkDataSource

        On Repo must be only catch Throwable
    */
    fun fetchUsers(): Flow<ApiResponse<List<UserDto>>> {
        return flow {
            emit(ApiResponse.Loading)
            val response = apiService.getUsers()

            if (response.isNotEmpty()) {
                emit(ApiResponse.Success(response))
            } else {
                emit(ApiResponse.Empty)
            }
        }.catch { e ->
            when (e) {
                is HttpException -> {
                    when (e.code()) {
                        403 -> {
                            Log.d("Repository", "getUsers: ${e.message}")
                            emit(ApiResponse.Error(e.message.toString()))
                        }
                    }
                }

                is Exception -> {
                    e.printStackTrace()
                    Log.d("Repository", "getUsers: ${e.message}")
                    emit(ApiResponse.Error(e.message.toString()))
                }

                else -> {
                    Log.d("Repository", "getUsers: ${e.message}")
                    emit(ApiResponse.Error(e.message.toString()))
                }
            }
        }
    }

    fun fetchDetailUser(username: String): Flow<ApiResponse<DetailUserDto>> {
        return flow {
            emit(ApiResponse.Loading)
            val response = apiService.getUserDetail(username)
            emit(ApiResponse.Success(response))
        }.catch { e ->
            when (e) {
                is HttpException -> {
                    when (e.code()) {
                        403 -> {
                            Log.d("Repository", "getUsers: ${e.message}")
                            emit(ApiResponse.Error(e.message.toString()))
                        }
                    }
                }

                is Exception -> {
                    e.printStackTrace()
                    Log.d("Repository", "getUsers: ${e.message}")
                    emit(ApiResponse.Error(e.message.toString()))
                }

                else -> {
                    Log.d("Repository", "getUsers: ${e.message}")
                    emit(ApiResponse.Error(e.message.toString()))
                }
            }
        }
    }

    fun fetchFollowers(username: String): Flow<ApiResponse<List<UserDto>>> {
        return flow {
            emit(ApiResponse.Loading)
            val response = apiService.getFollowers(username)

            if (response.isNotEmpty()) {
                emit(ApiResponse.Success(response))
            } else {
                emit(ApiResponse.Empty)
            }
        }.catch { e ->
            when (e) {
                is HttpException -> {
                    when (e.code()) {
                        403 -> {
                            Log.d("Repository", "getUsers: ${e.message}")
                            emit(ApiResponse.Error(e.message.toString()))
                        }
                    }
                }

                is Exception -> {
                    e.printStackTrace()
                    Log.d("Repository", "getUsers: ${e.message}")
                    emit(ApiResponse.Error(e.message.toString()))
                }

                else -> {
                    Log.d("Repository", "getUsers: ${e.message}")
                    emit(ApiResponse.Error(e.message.toString()))
                }
            }
        }
    }

    fun fetchFollowing(username: String): Flow<ApiResponse<List<UserDto>>> {
        return flow {
            emit(ApiResponse.Loading)
            val response = apiService.getFollowing(username)

            if (response.isNotEmpty()) {
                emit(ApiResponse.Success(response))
            } else {
                emit(ApiResponse.Empty)
            }
        }.catch { e ->
            when (e) {
                is HttpException -> {
                    when (e.code()) {
                        403 -> {
                            Log.d("Repository", "getUsers: ${e.message}")
                            emit(ApiResponse.Error(e.message.toString()))
                        }
                    }
                }

                is Exception -> {
                    e.printStackTrace()
                    Log.d("Repository", "getUsers: ${e.message}")
                    emit(ApiResponse.Error(e.message.toString()))
                }

                else -> {
                    Log.d("Repository", "getUsers: ${e.message}")
                    emit(ApiResponse.Error(e.message.toString()))
                }
            }
        }
    }
}