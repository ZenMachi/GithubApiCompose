package com.dokari4.githubapicompose.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DatastoreManager(context: Context) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "github_app_data")

    private val dataStore = context.dataStore

    val appThemeFlow: Flow<Theme> = dataStore.data.map { preferences ->
        Theme.fromValue(preferences[APP_THEME_KEY] ?: Theme.AUTO.value)
    }

    suspend fun setAppTheme(theme: Theme) {
        dataStore.edit { preferences ->
            preferences[APP_THEME_KEY] = theme.value
        }
    }

    companion object {
        private val APP_THEME_KEY = intPreferencesKey("app_theme")
    }
}