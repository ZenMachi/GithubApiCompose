package com.dokari4.githubapicompose.di

import android.content.Context
import com.dokari4.githubapicompose.data.local.DatastoreManager
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

    //TODO: Implement Room Dependency Injection
}