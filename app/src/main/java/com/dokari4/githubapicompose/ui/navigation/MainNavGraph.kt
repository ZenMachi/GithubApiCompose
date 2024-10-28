package com.dokari4.githubapicompose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dokari4.githubapicompose.ui.home.HomeScreen
import com.dokari4.githubapicompose.ui.search.SearchScreen
import com.dokari4.githubapicompose.ui.settings.SettingsScreen

@Composable
fun MainNavGraph(
    navController: NavHostController,
    rootNavController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = Routes.MainScreen.HomeScreen,
        modifier = modifier
    ) {
        composable<Routes.MainScreen.HomeScreen> {
            HomeScreen(
                onCardClick = {
                    rootNavController.navigate(Routes.DetailScreen(it)) {
                        launchSingleTop = true
                        restoreState = true
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                    }
                }
            )
        }
        composable<Routes.MainScreen.SearchScreen> {
            SearchScreen(
                onCardClick = { username ->
                    rootNavController.navigate(Routes.DetailScreen(username)) {
                        launchSingleTop = true
                        restoreState = true
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                    }
                }
            )
        }
        composable<Routes.MainScreen.SettingScreen> {
            SettingsScreen()
        }
    }
}