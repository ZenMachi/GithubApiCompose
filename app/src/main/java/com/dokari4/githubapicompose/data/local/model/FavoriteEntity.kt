package com.dokari4.githubapicompose.data.local.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "favorite_table")
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val userId: Int,
    val username: String,
    val avatarUrl: String,
) : Parcelable
