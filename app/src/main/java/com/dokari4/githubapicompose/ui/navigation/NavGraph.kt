package com.dokari4.githubapicompose.ui.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dokari4.githubapicompose.ui.components.BottomNavItem
import com.dokari4.githubapicompose.ui.home.HomeScreen
import com.dokari4.githubapicompose.ui.home.HomeViewModel

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Routes.HOME_SCREEN,
        modifier = modifier
    ) {
        composable(route = Routes.HOME_SCREEN) {
            HomeScreen(viewModel = viewModel)
        }
        composable(route = Routes.SEARCH_SCREEN) {
            Text("This is Search Screen")
        }
        composable(route = Routes.SETTING_SCREEN) {
            Text("This is Setting Screen")
        }
    }
}