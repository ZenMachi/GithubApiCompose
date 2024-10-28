package com.dokari4.githubapicompose.di

import android.content.Context
import androidx.room.Room
import com.dokari4.githubapicompose.data.local.database.AppDatabase
import com.dokari4.githubapicompose.data.local.database.FavoriteDao
import com.dokari4.githubapicompose.data.local.datastore.DatastoreManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideDatastore(@ApplicationContext context: Context): DatastoreManager = DatastoreManager(context)

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideUserDao(database: AppDatabase): FavoriteDao = database.favoriteDao()

}