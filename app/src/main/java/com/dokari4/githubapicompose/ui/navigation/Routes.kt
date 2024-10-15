package com.dokari4.githubapicompose.ui.navigation


sealed class Routes(val route: String) {
    data object MainScreen : Routes("main") {
        data object HomeScreen : Routes("home")
        data object SearchScreen : Routes("search")
        data object SettingScreen : Routes("setting")
    }
    data object DetailScreen : Routes("detail")

}