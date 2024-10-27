package com.dokari4.githubapicompose.data

import android.util.Log
import com.dokari4.githubapicompose.data.local.DatastoreManager
import com.dokari4.githubapicompose.data.local.Theme
import com.dokari4.githubapicompose.data.remote.dto.DetailUserDto
import com.dokari4.githubapicompose.data.remote.dto.SearchUserDto
import com.dokari4.githubapicompose.data.remote.dto.UserDto
import com.dokari4.githubapicompose.data.remote.network.ApiResponse
import com.dokari4.githubapicompose.data.remote.network.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val apiService: ApiService,
    private val datastore: DatastoreManager
) {
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

    fun searchUsers(query: String): Flow<ApiResponse<SearchUserDto>> {
        return flow {
            emit(ApiResponse.Loading)
            val response = apiService.searchUsers(query)
            val body = response.body()

            if (response.isSuccessful && body?.items?.isNotEmpty() == true) {
                emit(ApiResponse.Success(body))
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

    suspend fun saveAppTheme(theme: Theme) {
        datastore.setAppTheme(theme)
    }

    fun getAppTheme(): Flow<Theme> = datastore.appThemeFlow

    suspend fun saveDynamicColor(value: Boolean) {
        datastore.setDynamicColor(value)
    }

    fun getDynamicColor(): Flow<Boolean> = datastore.dynamicColorFlow

}