package com.dokari4.githubapicompose.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes(val route: String) {
    @Serializable
    data object MainScreen : Routes("main") {
        @Serializable
        data object HomeScreen : Routes("home")
        @Serializable
        data object SearchScreen : Routes("search")
        @Serializable
        data object SettingScreen : Routes("setting")
    }
    @Serializable
    data class DetailScreen(val username: String) : Routes("detail")
    @Serializable
    data object FavoritesScreen : Routes("favorites")
}