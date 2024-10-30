package com.dokari4.githubapicompose.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes {
    @Serializable
    data object MainScreen : Routes() {
        @Serializable
        data object HomeScreen : Routes()
        @Serializable
        data object SearchScreen : Routes()
        @Serializable
        data object SettingScreen : Routes()
    }
    @Serializable
    data class DetailScreen(val username: String) : Routes()
    @Serializable
    data object FavoritesScreen : Routes()
}