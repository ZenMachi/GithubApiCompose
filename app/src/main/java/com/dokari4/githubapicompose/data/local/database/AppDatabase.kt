package com.dokari4.githubapicompose.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dokari4.githubapicompose.data.local.model.FavoriteEntity

@Database(
    entities = [
        FavoriteEntity::class
    ],
    version = 1,
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao

}