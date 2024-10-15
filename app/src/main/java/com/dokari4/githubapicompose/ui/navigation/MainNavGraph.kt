package com.dokari4.githubapicompose.ui.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.dokari4.githubapicompose.MainScreen
import com.dokari4.githubapicompose.ui.detail.DetailScreen
import com.dokari4.githubapicompose.ui.home.HomeScreen
import com.dokari4.githubapicompose.ui.home.HomeViewModel
import com.dokari4.githubapicompose.ui.search.SearchScreen

@Composable
fun MainNavGraph(
    navController: NavHostController,
    rootNavController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Routes.MainScreen.HomeScreen.route,
        modifier = modifier
    ) {
        composable(route = Routes.MainScreen.HomeScreen.route) {
            HomeScreen(
                viewModel = viewModel,
                onCardClick = { rootNavController.navigate(Routes.DetailScreen.route) })
        }
        composable(route = Routes.MainScreen.SearchScreen.route) {
            SearchScreen()
        }
        composable(route = Routes.MainScreen.SettingScreen.route) {
            Text("This is Setting Screen")
        }
    }
}