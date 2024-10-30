package com.dokari4.githubapicompose.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.dokari4.githubapicompose.data.local.model.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favorite_table")
    fun getFavoritesList(): Flow<List<FavoriteEntity>>

    @Query("SELECT EXISTS(SELECT * FROM favorite_table WHERE username = :username)")
    fun checkFavorite(username: String): Flow<Boolean>

    @Insert
    suspend fun insertFavorite(favorite: FavoriteEntity)

    @Query("DELETE FROM favorite_table WHERE userId = :userId")
    suspend fun deleteFavorite(userId: Int)
}