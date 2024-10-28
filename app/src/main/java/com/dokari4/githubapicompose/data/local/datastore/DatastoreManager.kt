package com.dokari4.githubapicompose.data.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.dokari4.githubapicompose.utils.Theme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DatastoreManager(context: Context) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = APP_NAME)

    private val dataStore = context.dataStore

    val appThemeFlow: Flow<Theme> = dataStore.data.map { preferences ->
        Theme.fromValue(preferences[APP_THEME_KEY] ?: Theme.AUTO.value)
    }

    suspend fun setAppTheme(theme: Theme) {
        dataStore.edit { preferences ->
            preferences[APP_THEME_KEY] = theme.value
        }
    }

    val dynamicColorFlow: Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[DYNAMIC_COLOR_KEY] ?: true
    }

    suspend fun setDynamicColor(value: Boolean) {
        dataStore.edit { preferences ->
            preferences[DYNAMIC_COLOR_KEY] = value
        }
    }

    companion object {
        private const val APP_NAME = "github_app_data"
        private val APP_THEME_KEY = intPreferencesKey("app_theme")
        private val DYNAMIC_COLOR_KEY = booleanPreferencesKey("dynamic_color")
    }
}